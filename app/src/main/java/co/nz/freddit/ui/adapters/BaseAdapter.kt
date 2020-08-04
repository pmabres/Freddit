package co.nz.freddit.ui.adapters

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class BaseAdapter<VH : BaseAdapter.BaseViewHolder>(
    protected val context: Context) : RecyclerView.Adapter<VH>() {

    abstract class BaseViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val _clickListener = View.OnClickListener { view ->
            onClicked(view, adapterPosition)
        }
        init {
            itemView.setOnClickListener(_clickListener)
        }
        abstract fun onClicked(view: View, position: Int): Boolean
    }
}