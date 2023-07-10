package com.halilmasali.moviediscover.dataRepository.apiRepository.movies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ISimilarMoviesData {
    @GET("movie/{movie_id}/similar")
    fun createGet(
        @Header("Authorization") apiKey: String,
        @Path("movie_id") movieId: Int
    ): Call<MovieModelRoot>
}