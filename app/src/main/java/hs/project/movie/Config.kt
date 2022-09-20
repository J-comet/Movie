package hs.project.movie

object Config {

    const val SECRET_KEY = "f23e6b22e22652129739a9a883e85e46"
    const val BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

    const val NAVER_CLIENT_ID = "aiW8xH0RRQBC8wS9iiol"
    const val NAVER_SECRET = "Q_qtnHw0_c"
    const val NAVER_BASE_URL = "https://openapi.naver.com/v1/search/"

    interface API {
        companion object {
            const val DAILY = "searchDailyBoxOfficeList.json"
            const val WEEK = "searchWeeklyBoxOfficeList.json"
            const val NAVER_MOVIE = "movie.json"
        }
    }

}