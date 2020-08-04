package co.nz.freddit.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import co.nz.freddit.data.common.Post
import co.nz.freddit.databinding.PostListItemBinding

class PostsAdapter(context: Context) :
    SingleDataAdapter<Post, PostsAdapter.ViewHolder>(context) {
    inner class ViewHolder(private val itemBinding: PostListItemBinding) : SingleDataViewHolder<Post>(itemBinding.root) {
        override fun bindData(data: Post) {
            itemBinding.rvPostItemTitle.text = data.title
        }
    }

    override fun onViewHolderBound(data: Post, holder: ViewHolder, position: Int) {
        holder.bindData(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}