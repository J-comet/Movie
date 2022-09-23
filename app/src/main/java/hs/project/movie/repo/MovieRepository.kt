package hs.project.movie.repo

import hs.project.movie.api.MovieAPI
import hs.project.movie.api.RetrofitClient

class MovieRepository {

    private val client = RetrofitClient.getInstance().create(MovieAPI::class.java)

    suspend fun getPopularMovies(page: Int) = client.getPopularMovies(
        page = page
    )
}