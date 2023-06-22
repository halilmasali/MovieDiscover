package com.halilmasali.moviediscover.apiRepository.movies.nowPlaying

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface INowPlayingData {
    @GET("movie/now_playing")
    fun createGet(@Header("Authorization") apiKey:String):Call<NowPlayingModelRoot>
}