package alfianyusufabdullah.moviecatalogue.screen

import alfianyusufabdullah.moviecatalogue.R
import alfianyusufabdullah.moviecatalogue.common.ScreenType
import alfianyusufabdullah.moviecatalogue.entity.Movie
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MovieActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModel()
    private val movieAdapter: MovieAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        rvMovies.hasFixedSize()
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.adapter = movieAdapter

        movieViewModel.loadMovie(ScreenType.TV_SHOW)

        movieViewModel.movies.observe(this, observeMovie)
        movieViewModel.error.observe(this, observeError)
        movieViewModel.loading.observe(this, observeLoading)
    }

    private val observeMovie = Observer<List<Movie>>(movieAdapter::submitNewMovies)

    private val observeError = Observer<String>(this::log)

    private val observeLoading = Observer<Boolean>(this::log)

    private fun log(message: Any){
        Log.d(this::class.java.name, "$message")
    }
}