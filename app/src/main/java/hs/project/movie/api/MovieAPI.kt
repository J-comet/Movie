package hs.project.movie.api

import hs.project.movie.Config
import hs.project.movie.data.DetailPopularMovie
import hs.project.movie.data.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET(Config.API.MOVIE_POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Config.TM_SECRET_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = "ko"
    ): Response<PopularMovies>

    @GET(Config.API.DETAIL_MOVIE_POPULAR + "{movieId}")
    suspend fun getDetailPopularMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = Config.TM_SECRET_KEY,
        @Query("language") language: String = "ko"
    ): Response<DetailPopularMovie>
}