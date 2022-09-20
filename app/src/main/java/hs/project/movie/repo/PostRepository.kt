package hs.project.movie.repo

import hs.project.movie.Config
import hs.project.movie.api.PostAPI
import hs.project.movie.api.RetrofitClient

class PostRepository {

    private val client = RetrofitClient.getInstance().create(PostAPI::class.java)

    suspend fun getPosts(targetDt: String) = client.getPosts(
        secretKey = Config.SECRET_KEY,
        targetDt = targetDt
    )
}