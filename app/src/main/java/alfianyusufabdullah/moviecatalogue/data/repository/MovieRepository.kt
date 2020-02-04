package alfianyusufabdullah.moviecatalogue.data.repository

import alfianyusufabdullah.moviecatalogue.common.ScreenType
import alfianyusufabdullah.moviecatalogue.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun loadMovie(screenType: ScreenType): Flow<List<Movie>>
}