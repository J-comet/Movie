package hs.project.movie.data


import com.google.gson.annotations.SerializedName

data class ResponseNaverSearchPost(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<ResponseNaverSearchPostItem>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)

data class ResponseNaverSearchPostItem(
    @SerializedName("actor")
    val actor: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userRating")
    val userRating: String
)