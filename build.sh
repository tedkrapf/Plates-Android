#!/bin/bash
set -e

PROJECT="$(cd "$(dirname "$0")" && pwd)"
SRC="$PROJECT/app/src/main"
BUILD="$PROJECT/build"
ANDROID_JAR="/usr/lib/android-sdk/platforms/android-34/android.jar"
DX="/usr/lib/android-sdk/build-tools/debian/dx"

echo "=== Plates APK Build ==="
echo

# --- clean ---
rm -rf "$BUILD"
mkdir -p "$BUILD"/{gen,r_classes,dex}

# Strip Kotlin 1.8 module metadata from android.jar so kotlinc 1.3 can read it
ANDROID_JAR_CLEAN="$BUILD/android-clean.jar"
cp "$ANDROID_JAR" "$ANDROID_JAR_CLEAN"
zip -d "$ANDROID_JAR_CLEAN" 'META-INF/*.kotlin_module' 2>/dev/null || true

# --- 1. Generate R.java ---
echo "[1/7] Generating R.java..."
aapt package -f -m \
  -J "$BUILD/gen" \
  -M "$SRC/AndroidManifest.xml" \
  -S "$SRC/res" \
  -I "$ANDROID_JAR" \
  --version-code 1 \
  --version-name "1.0" \
  --min-sdk-version 26 \
  --target-sdk-version 34

# --- 2. Compile R.java ---
echo "[2/7] Compiling R.java..."
javac --release 8 \
  -classpath "$ANDROID_JAR" \
  -d "$BUILD/r_classes" \
  "$BUILD/gen/com/plates/app/R.java"

# --- 3. Compile Kotlin ---
echo "[3/7] Compiling Kotlin sources..."
SOURCES=$(find "$SRC/java" -name "*.kt" | sort)
kotlinc \
  -classpath "$ANDROID_JAR_CLEAN:$BUILD/r_classes" \
  -include-runtime \
  -d "$BUILD/app.jar" \
  $SOURCES
echo "   Kotlin compilation complete."

# --- 4. DEX ---
echo "[4/7] Converting to DEX..."
"$DX" --dex \
  --output="$BUILD/dex/classes.dex" \
  "$BUILD/app.jar" \
  "$BUILD/r_classes"

# --- 5. Package APK ---
echo "[5/7] Packaging APK..."
aapt package -f \
  -M "$SRC/AndroidManifest.xml" \
  -S "$SRC/res" \
  -I "$ANDROID_JAR" \
  --version-code 1 \
  --version-name "1.0" \
  -F "$BUILD/plates_raw.apk"

# Add DEX to APK (store without compression for runtime loading)
cd "$BUILD/dex"
zip -0 -j "$BUILD/plates_raw.apk" classes.dex
cd "$PROJECT"

# --- 6. Zipalign (must happen BEFORE signing with apksigner) ---
echo "[6/7] Aligning..."
zipalign -f 4 "$BUILD/plates_raw.apk" "$BUILD/plates_aligned.apk"

# --- 7. Sign ---
echo "[7/7] Signing..."
if [ ! -f "$PROJECT/debug.keystore" ]; then
  echo "   Generating debug keystore..."
  keytool -genkey -v \
    -keystore "$PROJECT/debug.keystore" \
    -alias android \
    -keyalg RSA -keysize 2048 -validity 10000 \
    -dname "CN=Plates Debug,O=Android,C=US" \
    -storepass android -keypass android
fi

apksigner sign \
  --ks "$PROJECT/debug.keystore" \
  --ks-pass pass:android \
  --key-pass pass:android \
  --ks-key-alias android \
  --out "$PROJECT/plates.apk" \
  "$BUILD/plates_aligned.apk"

echo
echo "=== BUILD SUCCESSFUL ==="
ls -lh "$PROJECT/plates.apk"
echo
echo "Install with:  adb install plates.apk"
