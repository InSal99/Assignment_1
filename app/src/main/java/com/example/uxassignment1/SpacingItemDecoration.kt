package com.example.uxassignment1

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartSpacingItemDecoration(context: Context, private val spaceInPx: Int) : RecyclerView.ItemDecoration() {

    private val space: Int

    init {
        val density = context.resources.displayMetrics.density
        space = (spaceInPx * density).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) {
            if (parent.layoutManager is LinearLayoutManager) {
                val layoutManager = parent.layoutManager as LinearLayoutManager
                if (layoutManager.orientation == RecyclerView.HORIZONTAL) {
                    outRect.left = space // Add space on the left for horizontal orientation
                } else {
                    outRect.top = space // Add space at the top for vertical orientation
                }
            }
        }
    }
}