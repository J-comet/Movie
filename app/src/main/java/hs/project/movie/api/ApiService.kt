package hs.project.movie.api

import hs.project.movie.Config
import hs.project.movie.data.model.DetailPopularMovie
import hs.project.movie.data.model.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Config.API.MOVIE_POPULAR)
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "ko"
    ): Response<PopularMovies>

    @GET(Config.API.DETAIL_MOVIE + "{movieId}")
    suspend fun getDetailPopularMovie(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "ko"
    ): Response<DetailPopularMovie>
}