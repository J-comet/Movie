package hs.project.movie.data

import hs.project.movie.api.ApiService
import hs.project.movie.data.model.DetailMovie
import hs.project.movie.data.model.PopularMovies
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getPopularMovies(
        page: Int,
        language: String = "ko"
    ): Response<PopularMovies> = apiService.getPopularMovies(page, language)

    suspend fun getDetailPopularMovie(
        movieId: Int,
        language: String = "ko"
    ): Response<DetailMovie> = apiService.getDetailPopularMovie(movieId, language)
}