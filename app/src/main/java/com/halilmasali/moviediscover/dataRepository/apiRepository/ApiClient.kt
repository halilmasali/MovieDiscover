package com.halilmasali.moviediscover.dataRepository.apiRepository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null || retrofit?.baseUrl().toString() != baseUrl)
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit as Retrofit
    }
}