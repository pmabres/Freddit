package co.nz.freddit.data.common

import co.nz.freddit.data.local.CommentDao
import co.nz.freddit.data.remote.CommentService
import javax.inject.Inject

class CommentRepository @Inject constructor (
    private val remoteDataSource: CommentService,
    private val localDataSource: CommentDao
) : BaseRepository() {
    fun getAllComments() = fetch({ localDataSource.getAll() }, { remoteDataSource.getAll()} , { it.let { localDataSource.insertAll(it!!) } })
}