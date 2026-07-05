package com.plates.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plates.app.data.PlateInfo

@Composable
fun LicensePlateCard(
    plate: PlateInfo,
    isSpotted: Boolean,
    isInteractive: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val bgColor = Color(plate.plateBackground)
    val borderColor = Color(plate.plateBorder)
    val textColor = Color(plate.plateText)
    val tagColor = Color(plate.taglineText)

    val overlayAlpha by animateColorAsState(
        targetValue = if (isSpotted) Color(0x55000000) else Color.Transparent,
        animationSpec = tween(200),
        label = "overlay"
    )

    Box(
        modifier = modifier
            .width(168.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(6.dp))
            .border(2.5.dp, borderColor, RoundedCornerShape(6.dp))
            .background(bgColor)
            .alpha(if (!isSpotted && isInteractive) 0.55f else 1f)
            .then(if (isInteractive) Modifier.clickable { onClick() } else Modifier)
    ) {
        // Plate content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Bolt row top
            BoltRow(borderColor)

            Spacer(Modifier.weight(1f))

            // Sample plate number
            Text(
                text = plate.sampleNumber,
                color = textColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.5.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(2.dp))

            // State name
            Text(
                text = plate.fullName.uppercase(),
                color = textColor,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Tagline
            Text(
                text = plate.tagline,
                color = tagColor.copy(alpha = 0.85f),
                fontSize = 7.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.weight(1f))

            // Bolt row bottom
            BoltRow(borderColor)
        }

        // Spotted overlay
        if (isSpotted) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(overlayAlpha),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Spotted",
                    tint = Color(0xFF00DD55),
                    modifier = Modifier
                        .padding(4.dp)
                        .size(22.dp)
                )
            }
        }

        // Canadian badge
        if (plate.isCanadian) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "🍁",
                    fontSize = 9.sp,
                    modifier = Modifier.padding(start = 4.dp, top = 3.dp)
                )
            }
        }
    }
}

@Composable
private fun BoltRow(boltColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Bolt(boltColor)
        Bolt(boltColor)
    }
}

@Composable
private fun Bolt(color: Color) {
    Box(
        modifier = Modifier
            .size(5.dp)
            .clip(CircleShape)
            .background(color.copy(alpha = 0.6f))
    )
}
