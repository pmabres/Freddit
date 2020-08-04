package co.nz.freddit.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.nz.freddit.data.common.Comment
import co.nz.freddit.data.common.Post

@Dao
interface PostDao : WritableDao<Post> {
    @Query("SELECT * FROM posts")
    override fun getAll() : LiveData<List<Post>>

    @Query("SELECT * FROM posts WHERE id = :id")
    override fun get(id: Long): LiveData<Post>

    @Query("SELECT * FROM comments WHERE postId = :id")
    fun getComments(id: Long): LiveData<List<Comment>>
}