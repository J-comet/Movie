package hs.project.movie.api

import hs.project.movie.Config
import hs.project.movie.data.ResponseDailyMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostAPI {

    @GET(Config.API.DAILY)
    suspend fun getPosts(
        @Query("key") secretKey: String,
        @Query("targetDt") targetDt: String
    ): Response<ResponseDailyMovies>

}