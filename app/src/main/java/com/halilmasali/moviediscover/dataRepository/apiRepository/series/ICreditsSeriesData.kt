package com.halilmasali.moviediscover.dataRepository.apiRepository.series

import com.halilmasali.moviediscover.dataRepository.apiRepository.creditsModel.CreditsModelRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ICreditsSeriesData {
    @GET("tv/{series_id}/credits")
    fun createGet(
        @Header("Authorization") apiKey: String,
        @Path("series_id") seriesId: Int
    ): Call<CreditsModelRoot>
}