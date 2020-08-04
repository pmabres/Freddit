package co.nz.freddit.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import co.nz.freddit.data.common.Comment
import co.nz.freddit.data.common.Post
import co.nz.freddit.databinding.CommentListItemBinding
import co.nz.freddit.databinding.PostListItemBinding

class CommentsAdapter(context: Context) :
    SingleDataAdapter<Comment, CommentsAdapter.ViewHolder>(context) {
    inner class ViewHolder(private val itemBinding: CommentListItemBinding) : SingleDataViewHolder<Comment>(itemBinding.root) {
        override fun bindData(data: Comment) {
            itemBinding.rvCommentBody.text = data.body
            itemBinding.rvCommentEmail.text = data.email
            itemBinding.rvCommentName.text = data.name
        }
    }

    override fun onViewHolderBound(data: Comment, holder: ViewHolder, position: Int) {
        holder.bindData(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CommentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}