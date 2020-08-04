package co.nz.freddit.core.modules

import co.nz.freddit.BuildConfig
import co.nz.freddit.data.remote.CommentService
import co.nz.freddit.data.remote.PostService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RestModule {
    @Singleton
    @Provides
    fun provideApi(gson: Gson, httpBuilder: OkHttpClient.Builder) =
        Retrofit.Builder()
            .client(httpBuilder.build())
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    @Provides
    fun provideHttpBuilder() = OkHttpClient.Builder()

    @Provides
    fun providesPostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)

    @Provides
    fun providesCommentService(retrofit: Retrofit): CommentService = retrofit.create(CommentService::class.java)
}