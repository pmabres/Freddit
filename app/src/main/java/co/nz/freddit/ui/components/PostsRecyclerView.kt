package co.nz.freddit.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import co.nz.freddit.data.common.Post
import co.nz.freddit.ui.adapters.PostsAdapter

class PostsRecyclerView :
    SingleDataRecyclerView<Post, PostsAdapter> {
    constructor(context: Context) : super(context)

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onInit(context: Context): PostsAdapter {
        this.addItemDecoration(VerticalSpaceDecoration(context))
        return PostsAdapter(context)
    }
}
