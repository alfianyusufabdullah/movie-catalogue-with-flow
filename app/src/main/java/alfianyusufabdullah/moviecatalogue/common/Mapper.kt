package alfianyusufabdullah.moviecatalogue.common

import alfianyusufabdullah.moviecatalogue.entity.Movie

class Mapper {

    fun nullOnListItemMapper(data: List<Movie>) =
        data
            .filter { it.backdropPath != null }
            .filter { it.posterPath != null }


    fun movieUrlImageMapper(data: List<Movie>) =
        data.map {
            it.copy().apply {
                backdropPath = "https://image.tmdb.org/t/p/w500${this.backdropPath}"
                posterPath = "https://image.tmdb.org/t/p/w500${this.posterPath}"
            }
        }

    fun movieNameMapper(data: List<Movie>) =
        data.map {
            val name = when {
                it.originalTitle.isNullOrBlank() && it.originalName != null -> it.originalName
                it.originalName.isNullOrBlank() && it.originalTitle != null -> it.originalTitle
                else -> "unnamed"
            }

            it.copy().apply {
                movieName = name
            }
        }

    fun movieDateMapper(data: List<Movie>) =
        data.map {
            val date = when {
                it.firstAirDate.isNullOrBlank() && it.releaseDate != null -> it.releaseDate
                it.releaseDate.isNullOrBlank() && it.firstAirDate != null -> it.firstAirDate
                else -> "unnamed"
            }

            it.copy().apply {
                movieReleaseDate = date
            }
        }
}