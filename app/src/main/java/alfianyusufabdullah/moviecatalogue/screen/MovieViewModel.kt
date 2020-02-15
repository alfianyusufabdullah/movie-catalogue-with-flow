package alfianyusufabdullah.moviecatalogue.screen

import alfianyusufabdullah.moviecatalogue.common.Mapper
import alfianyusufabdullah.moviecatalogue.common.ScreenType
import alfianyusufabdullah.moviecatalogue.data.repository.MovieRepository
import alfianyusufabdullah.moviecatalogue.entity.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieViewModel(private val repository: MovieRepository, private val mapper: Mapper) :
    ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val movies: LiveData<List<Movie>>
        get() = _movies

    val loading: LiveData<Boolean>
        get() = _loading

    val error: LiveData<String>
        get() = _error

    @ExperimentalCoroutinesApi
    fun loadMovie(screenType: ScreenType) {

        viewModelScope.launch {
            repository.loadMovie(screenType)
                .onStart { _loading.postValue(true) }
                .map { mapper.nullOnListItemMapper(it) }
                .map { mapper.movieUrlImageMapper(it) }
                .map { mapper.movieNameMapper(it) }
                .map { mapper.movieDateMapper(it) }
                .catch { _error.postValue(it.message) }
                .onCompletion { _loading.postValue(false) }
                .collect { _movies.postValue(it) }
        }
    }
}