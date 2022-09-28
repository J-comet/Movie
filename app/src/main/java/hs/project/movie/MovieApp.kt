package hs.project.movie

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class MovieApp : Application() {

    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate()
    }
}