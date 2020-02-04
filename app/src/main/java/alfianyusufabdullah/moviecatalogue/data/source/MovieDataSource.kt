package alfianyusufabdullah.moviecatalogue.data.source

import alfianyusufabdullah.moviecatalogue.common.ScreenType
import java.net.URL

class MovieDataSource {

    fun loadMovie(screenType: ScreenType): String {
        return when (screenType) {
            ScreenType.MOVIE -> URL("https://api.themoviedb.org/3/discover/movie?api_key=b650046bf640e7bf7054093854b8d02a").readText()
            ScreenType.TV_SHOW -> URL("https://api.themoviedb.org/3/discover/tv?api_key=b650046bf640e7bf7054093854b8d02a").readText()
        }
    }
}