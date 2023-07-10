package com.halilmasali.moviediscover.dataRepository.apiRepository.series

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IOnTheAirData {
    @GET("tv/on_the_air")
    fun createGet(@Header("Authorization") apiKey: String): Call<SeriesModelRoot>
}