package com.halilmasali.moviediscover.apiRepository.movies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ITopRatedData {
    @GET("movie/top_rated")
    fun createGet(@Header("Authorization") apiKey:String): Call<MovieModelRoot>
}