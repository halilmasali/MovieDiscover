package com.halilmasali.moviediscover.apiRepository.series

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ISimilarSeriesData {
    @GET("tv/{series_id}/similar")
    fun createGet(
        @Header("Authorization") apiKey: String,
        @Path("series_id") seriesId: String
    ): Call<SeriesModelRoot>
}