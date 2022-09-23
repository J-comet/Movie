package hs.project.movie.repo

import hs.project.movie.Config
import hs.project.movie.api.PostAPI
import hs.project.movie.api.RetrofitClient22

class PostRepository {

    private val client = RetrofitClient22.getInstance().create(PostAPI::class.java)

    suspend fun getPosts(targetDt: String) = client.getPosts(
        secretKey = Config.SECRET_KEY,
        targetDt = targetDt
    )
}