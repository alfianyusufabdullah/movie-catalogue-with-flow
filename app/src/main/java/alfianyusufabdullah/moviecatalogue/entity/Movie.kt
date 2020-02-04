package alfianyusufabdullah.moviecatalogue.entity

import com.google.gson.annotations.SerializedName

data class Movie(

    @field:SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("poster_path")
    var posterPath: String? = null,

    @field:SerializedName("first_air_date")
    var firstAirDate: String? = null,

    @field:SerializedName("release_date")
    var releaseDate: String? = null,

    var movieName: String? = null,

    var movieReleaseDate: String? = null
)