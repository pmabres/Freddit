package co.nz.freddit.ui.components

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.nz.freddit.ui.adapters.BaseAdapter
import kotlin.math.floor

abstract class BaseRecyclerView<T : BaseAdapter<*>> : RecyclerView {
    var baseAdapter: T? = null
        private set

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    abstract fun onInit(context: Context): T

    private fun init(context: Context) {
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
        isNestedScrollingEnabled = false
        baseAdapter = onInit(context)
    }

    inner class VerticalSpaceDecoration : ItemDecoration {
        private val defaultVerticalSpace = 16
        private val verticalSpaceHeight: Int

        constructor(context: Context) {
            this.verticalSpaceHeight = calculateVerticalSpaceDPtoPX(context, defaultVerticalSpace)
        }

        constructor(context: Context, verticalSpaceHeight: Int) {
            this.verticalSpaceHeight = calculateVerticalSpaceDPtoPX(context, verticalSpaceHeight)
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
            outRect.bottom = verticalSpaceHeight
        }

        private fun calculateVerticalSpaceDPtoPX(context: Context, dp: Int): Int {
            val metrics = context.resources.displayMetrics
            return floor(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), metrics).toDouble())
                .toInt()
        }
    }
}
