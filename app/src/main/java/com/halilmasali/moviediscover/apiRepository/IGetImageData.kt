package com.halilmasali.moviediscover.apiRepository

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface IGetImageData {
    @GET("{image_size}/{image_path}")
    @Streaming
    fun createGet(
        @Path("image_size") imageSize: String,
        @Path("image_path") imagePath: String
    ): Call<ResponseBody>
}