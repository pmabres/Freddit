package co.nz.freddit.core.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object SerializeModule {
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
}