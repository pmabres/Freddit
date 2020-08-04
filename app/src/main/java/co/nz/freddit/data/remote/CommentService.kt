package co.nz.freddit.data.remote

import co.nz.freddit.data.common.Comment
import retrofit2.Response
import retrofit2.http.GET

interface CommentService {
    @GET("comments")
    suspend fun getAll() : Response<List<Comment>>
}