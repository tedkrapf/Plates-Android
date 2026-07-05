package com.plates.app.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.plates.app.data.PlateInfo

class LicensePlateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    var plate: PlateInfo? = null
        set(value) { field = value; invalidate() }

    var isSpotted: Boolean = false
        set(value) { field = value; invalidate() }

    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        typeface = Typeface.MONOSPACE
    }
    private val namePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    private val tagPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val boltPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val overlayPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#88000000")
    }
    private val checkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FF00DD55")
        style = Paint.Style.STROKE
        strokeWidth = 6f
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
    private val rect = RectF()

    override fun onDraw(canvas: Canvas) {
        val p = plate ?: return
        val w = width.toFloat()
        val h = height.toFloat()
        val pad = 6f
        val r = 18f

        val bg = Color.parseColor(p.bgColor)
        val border = Color.parseColor(p.borderColor)
        val text = Color.parseColor(p.textColor)
        val tag = Color.parseColor(p.taglineColor)

        // Background
        bgPaint.color = bg
        rect.set(pad, pad, w - pad, h - pad)
        canvas.drawRoundRect(rect, r, r, bgPaint)

        // Border
        borderPaint.color = border
        canvas.drawRoundRect(rect, r, r, borderPaint)

        // Bolt holes
        val boltR = 5f
        val boltOff = 14f
        boltPaint.color = border
        boltPaint.alpha = 180
        canvas.drawCircle(pad + boltOff, pad + boltOff, boltR, boltPaint)
        canvas.drawCircle(w - pad - boltOff, pad + boltOff, boltR, boltPaint)
        canvas.drawCircle(pad + boltOff, h - pad - boltOff, boltR, boltPaint)
        canvas.drawCircle(w - pad - boltOff, h - pad - boltOff, boltR, boltPaint)

        // Plate number (large, centered)
        textPaint.color = text
        textPaint.textSize = h * 0.30f
        textPaint.isFakeBoldText = true
        textPaint.letterSpacing = 0.1f
        val numWidth = textPaint.measureText(p.sampleNumber)
        canvas.drawText(p.sampleNumber, (w - numWidth) / 2f, h * 0.48f, textPaint)

        // State name
        namePaint.color = text
        namePaint.textSize = h * 0.145f
        namePaint.letterSpacing = 0.05f
        val fullName = if (p.fullName.length > 16) p.code else p.fullName.toUpperCase()
        val nameWidth = namePaint.measureText(fullName)
        canvas.drawText(fullName, (w - nameWidth) / 2f, h * 0.66f, namePaint)

        // Tagline
        tagPaint.color = tag
        tagPaint.alpha = 200
        tagPaint.textSize = h * 0.095f
        val tagStr = if (p.tagline.length > 24) p.tagline.substring(0, 22) + "…" else p.tagline
        val tagWidth = tagPaint.measureText(tagStr)
        canvas.drawText(tagStr, (w - tagWidth) / 2f, h * 0.82f, tagPaint)

        // Canadian maple leaf indicator
        if (p.isCanadian) {
            tagPaint.color = Color.RED
            tagPaint.alpha = 180
            tagPaint.textSize = h * 0.15f
            canvas.drawText("🍁", pad + 10f, pad + h * 0.22f, tagPaint)
        }

        // Spotted overlay + check
        if (isSpotted) {
            canvas.drawRoundRect(rect, r, r, overlayPaint)
            // Draw checkmark in top-right
            val cx = w - pad - 26f
            val cy = pad + 26f
            val cs = 14f
            checkPaint.color = Color.parseColor("#FF00DD55")
            canvas.drawLine(cx - cs, cy, cx - cs * 0.3f, cy + cs * 0.7f, checkPaint)
            canvas.drawLine(cx - cs * 0.3f, cy + cs * 0.7f, cx + cs, cy - cs * 0.8f, checkPaint)
        }

        // Dimmed if not spotted
        if (!isSpotted) {
            val dimPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            dimPaint.color = Color.parseColor("#88FFFFFF")
            canvas.drawRoundRect(rect, r, r, dimPaint)
        }
    }
}
