package hs.project.movie.data.repository

import hs.project.movie.data.RemoteDataSource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getPopularMovies(page: Int) = remoteDataSource.getPopularMovies(
        page = page
    )

    suspend fun getDetailPopularMovie(movieId: Int) = remoteDataSource.getDetailPopularMovie(
        movieId = movieId
    )
}