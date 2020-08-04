package co.nz.freddit.ui.adapters

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes

abstract class SingleDataAdapter<T, VH : BaseAdapter.BaseViewHolder>(context: Context) :
    BaseAdapter<VH>(context) {
    private var _clickListener: ((view: View, position: Int, item: T) -> Unit)? = null

    fun setOnItemClickListener(itemClickListener: (view: View, position: Int, item: T) -> Unit) {
        this._clickListener = itemClickListener
    }

    private var _data: List<T>? = null

    fun setData(data: List<T>) {
        this._data = data
    }

    abstract fun onViewHolderBound(data: T, holder: VH, position: Int)

    override fun onBindViewHolder(holder: VH, position: Int) {
        onViewHolderBound(getItem(position), holder, position)
    }

    // total number of rows
    override fun getItemCount(): Int {
        if (_data == null) {
            return 0
        }
        return _data!!.size
    }

    fun getItem(id: Int): T {
        return _data!![id]
    }

    abstract inner class SingleDataViewHolder<T> internal constructor(itemView: View) : BaseAdapter.BaseViewHolder(itemView) {
        override fun onClicked(view: View, position: Int): Boolean {
            if (_clickListener != null) {
                _clickListener?.invoke(view, position, getItem(position))
            }
            return true
        }
        abstract fun bindData(data: T)
    }
}
