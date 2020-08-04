package co.nz.freddit.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import co.nz.freddit.data.common.Comment
import co.nz.freddit.data.common.Post
import co.nz.freddit.ui.adapters.CommentsAdapter
import co.nz.freddit.ui.adapters.PostsAdapter

class CommentsRecyclerView :
    SingleDataRecyclerView<Comment, CommentsAdapter> {
    constructor(context: Context) : super(context)

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onInit(context: Context): CommentsAdapter {
        return CommentsAdapter(context)
    }
}
