package hs.project.movie.api

import com.google.gson.GsonBuilder
import hs.project.movie.Config
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {

    private val gson = GsonBuilder().setLenient().create()


    private val client by lazy {

        val httpClient = OkHttpClient.Builder()

        httpClient.interceptors().add(Interceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder().addQueryParameter("api_key", Config.TM_SECRET_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })

        Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    fun getInstance(): Retrofit {
        return client
    }
}
