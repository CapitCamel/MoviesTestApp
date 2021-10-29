package com.example.moviestestapp.data


data class PopularMovies(
    val results: List<Result>,
)

data class Result(
    val id: Int,
    val title: String,
    val backdrop_path: String
)

data class DetailsMovie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val backdrop_path: String,
    val overview: String

)
