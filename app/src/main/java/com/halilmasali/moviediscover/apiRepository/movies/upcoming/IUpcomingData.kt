package com.halilmasali.moviediscover.apiRepository.movies.upcoming

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IUpcomingData {
    @GET("movie/upcoming")
    fun createGet(@Header("Authorization") apiKey:String): Call<UpcomingModelRoot>
}