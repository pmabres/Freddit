package co.nz.freddit.data.common

import androidx.room.*

@Entity(
    tableName = "comments",
    foreignKeys = [ForeignKey(
        entity = Post::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("postId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(index = true)
    val postId: Long,
    val name: String,
    val email: String,
    val body: String)

class PostWithComments {
    @Embedded
    var post: Post? = null

    @Relation(parentColumn = "id", entityColumn = "postId", entity = Comment::class)
    var comments: Collection<Comment>? = null
}