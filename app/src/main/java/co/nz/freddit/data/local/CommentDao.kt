package co.nz.freddit.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import co.nz.freddit.data.common.Comment

@Dao
interface CommentDao : WritableDao<Comment> {
    @Query("SELECT * FROM comments")
    override fun getAll() : LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE id = :id")
    override fun get(id: Long): LiveData<Comment>
}