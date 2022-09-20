package hs.project.movie.repo

import hs.project.movie.Config
import hs.project.movie.api.NaverPostAPI
import hs.project.movie.api.RetrofitNaverClient
import retrofit2.http.Header
import retrofit2.http.Query

class NaverPostRepository {

    private val client = RetrofitNaverClient.getInstance().create(NaverPostAPI::class.java)

    suspend fun getPosts(query: String) = client.getPosts(
        clientId = Config.NAVER_CLIENT_ID,
        clientSecret = Config.NAVER_SECRET,
        query = query,
        display = 50
    )
}