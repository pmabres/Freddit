package co.nz.freddit.ui.posts

import androidx.hilt.lifecycle.ViewModelInject
import co.nz.freddit.core.base.BaseViewModel
import co.nz.freddit.data.common.PostRepository

class PostCollectionViewModel @ViewModelInject constructor(
    repository: PostRepository
) : BaseViewModel() {
    val posts = repository.getAllPosts()
}
