package hs.project.movie.repo

import hs.project.movie.Config
import hs.project.movie.api.RetrofitClient
import hs.project.movie.data.ResponseDailyMovies

class PostRepository {

    companion object {
        val instance = PostRepository()
    }

    suspend fun getPosts(targetDt: String): ResponseDailyMovies? {
        val response = RetrofitClient.postAPI.getPosts(
            secretKey = Config.SECRET_KEY,
            targetDt = targetDt
        )
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}