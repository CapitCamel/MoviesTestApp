package com.example.moviestestapp.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    suspend fun getMovies(@Query("api_key") key: String,
                          @Query("page") page: Int,
                          @Query("per_page") itemsPerPage: Int): PopularMovies

    @GET("3/movie/{movie_id}")
    suspend fun getDetails(@Path("movie_id") id: Int,
                           @Query("api_key") key: String): DetailsMovie
}
