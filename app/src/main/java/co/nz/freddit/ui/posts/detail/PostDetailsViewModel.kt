package co.nz.freddit.ui.posts.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import co.nz.freddit.data.common.Comment
import co.nz.freddit.data.common.PostRepository
import co.nz.freddit.data.remote.Result
class PostDetailsViewModel @ViewModelInject constructor(
    private val repository: PostRepository
) : ViewModel() {
    fun fetchComments(id: Long) = repository.getComments(id)
}
