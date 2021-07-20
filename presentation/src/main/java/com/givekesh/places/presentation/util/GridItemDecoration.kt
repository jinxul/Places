package com.givekesh.places.presentation.util

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class GridItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (state.itemCount >= 4) {
            val marginTop = when (parent.getChildLayoutPosition(view)) {
                0 -> TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    56F,
                    view.context.resources.displayMetrics
                ).toInt()
                else -> view.marginTop
            }
            (view.layoutParams as StaggeredGridLayoutManager.LayoutParams)
                .topMargin = marginTop
        }
    }
}