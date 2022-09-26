package hs.project.movie

object Config {

    const val TM_SECRET_KEY = "1e67cda0cb3b2280706d2a453e7b2f93"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMG_BASE_URL = "https://image.tmdb.org/t/p/w500"

    const val SECRET_KEY = "f23e6b22e22652129739a9a883e85e46"
    const val BOXOFFICE_BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" // 영화진흥위원회

    const val NAVER_CLIENT_ID = "aiW8xH0RRQBC8wS9iiol"
    const val NAVER_SECRET = "Q_qtnHw0_c"
    const val NAVER_BASE_URL = "https://openapi.naver.com/v1/search/"

    interface API {
        companion object {
            const val DAILY = "searchDailyBoxOfficeList.json"
            const val WEEK = "searchWeeklyBoxOfficeList.json"
            const val NAVER_MOVIE = "movie.json"



            const val MOVIE_POPULAR = "movie/popular"
            const val DETAIL_MOVIE_POPULAR = "movie/"
        }
    }

}