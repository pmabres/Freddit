package co.nz.freddit.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import co.nz.freddit.ui.adapters.SingleDataAdapter

abstract class SingleDataRecyclerView<T, A : SingleDataAdapter<T, *>> : BaseRecyclerView<A> {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    fun setData(data: List<T>) {
        baseAdapter!!.setData(data)
        if (adapter == null) {
            adapter = baseAdapter
        }
        baseAdapter!!.notifyDataSetChanged()
    }
    fun setOnItemClickListener(itemClickListener: (view: View, position: Int, item: T) -> Unit) {
        baseAdapter?.setOnItemClickListener(itemClickListener)
    }
}
