package com.halilmasali.moviediscover.apiRepository.series

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IAiringTodayData {
    @GET("tv/airing_today")
    fun createGet(@Header("Authorization") apiKey: String): Call<SeriesModelRoot>
}