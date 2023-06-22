package com.halilmasali.moviediscover.apiRepository.movies.popular

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IPopularData {
    @GET("movie/popular")
    fun createGet(@Header("Authorization") apiKey:String): Call<PopularModelRoot>
}