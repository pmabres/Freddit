package co.nz.freddit.data.common

import android.util.Log
import co.nz.freddit.data.local.CommentDao
import co.nz.freddit.data.local.PostDao
import co.nz.freddit.data.remote.PostService
import javax.inject.Inject

class PostRepository @Inject constructor (
    private val remoteDataSource: PostService,
    private val localDataSource: PostDao,
    private val localCommentDataSource: CommentDao
) : BaseRepository() {
    fun getAllPosts() = fetch(
        { localDataSource.getAll() },
        { remoteDataSource.getAll() } ,
        { it.let { localDataSource.insertAll(it!!) }
    })
    fun getPost(id: Long) = fetch(
        { localDataSource.get(id) },
        { remoteDataSource.get(id)} ,
        { it.let { localDataSource.insert(it!!) }
    })
    fun getComments(id: Long) = fetch({ localDataSource.getComments(id) },
        { remoteDataSource.getComments(id)} ,
        { it.let { localCommentDataSource.insertAll(it!!) }
    })
}