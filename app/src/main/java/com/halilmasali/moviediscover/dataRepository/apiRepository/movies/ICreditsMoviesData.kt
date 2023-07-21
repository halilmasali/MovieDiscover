package com.halilmasali.moviediscover.dataRepository.apiRepository.movies

import com.halilmasali.moviediscover.dataRepository.apiRepository.creditsModel.CreditsModelRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ICreditsMoviesData {
    @GET("movie/{movie_id}/credits")
    fun createGet(
        @Header("Authorization") apiKey: String,
        @Path("movie_id") movieId: Int
    ): Call<CreditsModelRoot>
}