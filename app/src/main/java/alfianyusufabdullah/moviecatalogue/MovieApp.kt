package alfianyusufabdullah.moviecatalogue

import alfianyusufabdullah.moviecatalogue.common.Mapper
import alfianyusufabdullah.moviecatalogue.data.repository.MovieRepository
import alfianyusufabdullah.moviecatalogue.data.repository.MovieRepositoryImpl
import alfianyusufabdullah.moviecatalogue.data.source.MovieDataSource
import alfianyusufabdullah.moviecatalogue.screen.MovieAdapter
import alfianyusufabdullah.moviecatalogue.screen.MovieViewModel
import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


@ExperimentalCoroutinesApi
class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            modules(movieModule)
        }
    }
}

@ExperimentalCoroutinesApi
val movieModule = module {

    single { MovieDataSource() }
    single { Mapper() }

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    single { MovieAdapter(mutableListOf()) }

    viewModel { MovieViewModel(get(), get()) }
}