package co.nz.freddit.core.modules

import co.nz.freddit.data.common.CommentRepository
import co.nz.freddit.data.common.PostRepository
import co.nz.freddit.data.local.CommentDao
import co.nz.freddit.data.local.PostDao
import co.nz.freddit.data.remote.CommentService
import co.nz.freddit.data.remote.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePostRepository(remoteDataSource: PostService,
                              localDataSource: PostDao,
                              localCommentDataSource: CommentDao) =
        PostRepository(remoteDataSource, localDataSource, localCommentDataSource)

    @Singleton
    @Provides
    fun provideCommentRepository(remoteDataSource: CommentService,
                                 localDataSource: CommentDao) =
        CommentRepository(remoteDataSource, localDataSource)
}
