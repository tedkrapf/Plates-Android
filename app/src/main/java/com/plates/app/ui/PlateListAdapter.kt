package com.plates.app.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.plates.app.data.PlateInfo

class PlateListAdapter(
    private val context: Context,
    sections: List<Pair<String, List<PlateInfo>>>,
    private val spottedCodes: MutableSet<String>,
    private val onToggle: (String, Boolean) -> Unit,
    private val onScoreChanged: () -> Unit,
    private val readOnly: Boolean = false
) : BaseAdapter() {

    private val density = context.resources.displayMetrics.density

    private sealed class Item {
        data class Header(val title: String) : Item()
        data class PlateRow(val left: PlateInfo, val right: PlateInfo?) : Item()
    }

    private val items: List<Item>

    init {
        val list = mutableListOf<Item>()
        for ((title, plates) in sections) {
            list.add(Item.Header(title))
            plates.chunked(2).forEach { chunk ->
                list.add(Item.PlateRow(chunk[0], chunk.getOrNull(1)))
            }
        }
        items = list
    }

    override fun getCount() = items.size
    override fun getItem(position: Int): Any = items[position]
    override fun getItemId(position: Int) = position.toLong()
    override fun getViewTypeCount() = 2
    override fun getItemViewType(position: Int) = if (items[position] is Item.Header) 0 else 1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return when (val item = items[position]) {
            is Item.Header -> makeHeader(item.title)
            is Item.PlateRow -> makePlateRow(item)
        }
    }

    private fun makeHeader(title: String): TextView {
        return TextView(context).apply {
            text = title
            textSize = 12f
            setTextColor(Color.parseColor("#FF555555"))
            setBackgroundColor(Color.parseColor("#FFE8E8E8"))
            setPadding(dp(14), dp(8), dp(14), dp(8))
            gravity = Gravity.CENTER_VERTICAL
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }
    }

    private fun makePlateRow(row: Item.PlateRow): View {
        val plateHeight = dp(100)
        val rowLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(dp(6), dp(5), dp(6), dp(5))
        }
        addPlateToRow(rowLayout, row.left, plateHeight)
        if (row.right != null) {
            addPlateToRow(rowLayout, row.right, plateHeight)
        } else {
            val spacer = View(context)
            spacer.layoutParams = LinearLayout.LayoutParams(0, plateHeight, 1f).also {
                it.setMargins(dp(4), 0, dp(4), 0)
            }
            rowLayout.addView(spacer)
        }
        return rowLayout
    }

    private fun addPlateToRow(row: LinearLayout, plate: PlateInfo, height: Int) {
        val pv = LicensePlateView(context)
        pv.plate = plate
        pv.isSpotted = plate.code in spottedCodes
        pv.layoutParams = LinearLayout.LayoutParams(0, height, 1f).also {
            it.setMargins(dp(4), 0, dp(4), 0)
        }
        if (!readOnly) {
            pv.setOnClickListener {
                val nowSpotted = plate.code !in spottedCodes
                if (nowSpotted) spottedCodes.add(plate.code) else spottedCodes.remove(plate.code)
                pv.isSpotted = nowSpotted
                onToggle(plate.code, nowSpotted)
                onScoreChanged()
            }
        }
        row.addView(pv)
    }

    private fun dp(value: Int): Int = (value * density + 0.5f).toInt()
}
