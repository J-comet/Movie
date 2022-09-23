package hs.project.movie.api

import hs.project.movie.Config
import hs.project.movie.data.PopularMovies
import hs.project.movie.data.ResponseDailyMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(Config.API.MOVIE_POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Config.TM_SECRET_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = "ko"
    ): Response<PopularMovies>

}