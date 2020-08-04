package co.nz.freddit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FredditApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}