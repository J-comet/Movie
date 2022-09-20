package hs.project.movie

object Config {

    const val SECRET_KEY = "f23e6b22e22652129739a9a883e85e46"
    const val BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

    interface API {
        companion object {
            const val DAILY = "searchDailyBoxOfficeList.json"
            const val WEEK = "searchWeeklyBoxOfficeList.json"
        }
    }
}