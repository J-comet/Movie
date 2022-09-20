package hs.project.movie.api

import hs.project.movie.Config
import hs.project.movie.data.ResponseNaverSearchPost
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverPostAPI {

    @GET(Config.API.NAVER_MOVIE)
    suspend fun getPosts(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int,
    ): Response<ResponseNaverSearchPost>

}