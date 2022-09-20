package hs.project.movie.api

import com.google.gson.GsonBuilder
import hs.project.movie.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {

    val client by lazy {

        val baseUrl = Config.BASE_URL

        val gson = GsonBuilder().setLenient().create()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val postAPI by lazy {
        client.create(PostAPI::class.java)
    }
}
