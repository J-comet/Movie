package hs.project.movie.repo

import hs.project.movie.api.MovieAPI
import javax.inject.Inject

class MovieRepository
@Inject
constructor(private val api: MovieAPI) {

    suspend fun getPopularMovies(page: Int) = api.getPopularMovies(
        page = page
    )

    suspend fun getDetailPopularMovie(movieId: Int) = api.getDetailPopularMovie(
        movieId = movieId
    )
}