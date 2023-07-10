package com.halilmasali.moviediscover.dataRepository.apiRepository.series

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ISeriesPopularData {
    @GET("tv/popular")
    fun createGet(@Header("Authorization") apiKey: String): Call<SeriesModelRoot>
}