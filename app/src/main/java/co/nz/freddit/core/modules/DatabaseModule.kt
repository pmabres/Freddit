package co.nz.freddit.core.modules

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.nz.freddit.data.common.Comment
import co.nz.freddit.data.common.PostRepository
import co.nz.freddit.data.common.Post
import co.nz.freddit.data.local.CommentDao
import co.nz.freddit.data.local.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = FredditDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCommentDao(db: FredditDatabase) = db.commentDao()

    @Singleton
    @Provides
    fun providePostDao(db: FredditDatabase) = db.postDao()
}

@Database(entities = [Comment::class, Post::class], version = 1, exportSchema = false)
abstract class FredditDatabase : RoomDatabase() {

    abstract fun commentDao(): CommentDao
    abstract fun postDao(): PostDao

    companion object {
        @Volatile private var instance: FredditDatabase? = null

        fun getDatabase(context: Context): FredditDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, FredditDatabase::class.java, "freddit")
                .build()
    }

}