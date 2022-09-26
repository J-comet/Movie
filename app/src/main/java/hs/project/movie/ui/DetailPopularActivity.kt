package hs.project.movie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hs.project.movie.R
import hs.project.movie.databinding.ActivityDetailPopularBinding

class DetailPopularActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailPopularBinding.inflate(layoutInflater)
    }


    private var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        id = intent.getIntExtra("id", -1)
        Log.d("========================= ${this.javaClass.name} =========================", "id = $id")
    }
}