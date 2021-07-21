package com.givekesh.places.presentation.util

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class GridItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let { adapter ->
            if (adapter.itemCount >= 4) {
                val marginTop = when (parent.getChildLayoutPosition(view)) {
                    0 -> getPixelSize(56F, view.context)
                    else -> getPixelSize(8F, view.context)
                }
                (view.layoutParams as StaggeredGridLayoutManager.LayoutParams)
                    .topMargin = marginTop
            }
        }
    }

    private fun getPixelSize(dp: Float, context: Context): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}