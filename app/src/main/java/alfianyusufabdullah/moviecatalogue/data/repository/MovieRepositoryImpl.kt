package alfianyusufabdullah.moviecatalogue.data.repository

import alfianyusufabdullah.moviecatalogue.common.ScreenType
import alfianyusufabdullah.moviecatalogue.data.source.MovieDataSource
import alfianyusufabdullah.moviecatalogue.entity.Movies
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
class MovieRepositoryImpl(private val dataSource: MovieDataSource) :
    MovieRepository {

    override fun loadMovie(screenType: ScreenType) = flow {
        val response = Gson().fromJson(dataSource.loadMovie(screenType), Movies::class.java)
        emit(response.results)
    }.flowOn(Dispatchers.IO)
}