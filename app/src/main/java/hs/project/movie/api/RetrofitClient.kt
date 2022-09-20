package hs.project.movie.api

import com.google.gson.GsonBuilder
import hs.project.movie.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {

    private val gson = GsonBuilder().setLenient().create()
    private val client =
        Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun getInstance(): Retrofit {
        return client
    }
}
