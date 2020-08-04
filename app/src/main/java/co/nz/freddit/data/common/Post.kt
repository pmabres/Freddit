package co.nz.freddit.data.common

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val userId: Int,
    val title: String,
    val body: String)