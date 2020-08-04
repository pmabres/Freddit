package co.nz.freddit.data.remote

import co.nz.freddit.data.common.Comment
import co.nz.freddit.data.common.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    suspend fun getAll() : Response<List<Post>>

    @GET("posts/{id}")
    suspend fun get(@Path("id") id: Long): Response<Post>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Long): Response<List<Comment>>
}