package alfianyusufabdullah.moviecatalogue.entity

import com.google.gson.annotations.SerializedName

data class Movies(

    @field:SerializedName("results")
    val results: List<Movie> = listOf()
)