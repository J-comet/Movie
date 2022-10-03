package hs.project.movie

object Config {

    const val TM_SECRET_KEY = "1e67cda0cb3b2280706d2a453e7b2f93"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMG_BASE_URL = "https://image.tmdb.org/t/p/w500"

    interface API {
        companion object {
//            const val DAILY = "searchDailyBoxOfficeList.json"
//            const val WEEK = "searchWeeklyBoxOfficeList.json"
//            const val NAVER_MOVIE = "movie.json"

            const val MOVIE_POPULAR = "movie/popular"
            const val DETAIL_MOVIE = "movie/"
        }
    }

}