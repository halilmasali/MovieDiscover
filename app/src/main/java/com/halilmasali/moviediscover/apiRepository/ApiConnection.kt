package com.halilmasali.moviediscover.apiRepository

import androidx.lifecycle.MutableLiveData
import com.halilmasali.moviediscover.apiRepository.movies.nowPlaying.INowPlayingData
import com.halilmasali.moviediscover.apiRepository.movies.nowPlaying.NowPlayingModelRoot
import com.halilmasali.moviediscover.apiRepository.movies.popular.IPopularData
import com.halilmasali.moviediscover.apiRepository.movies.popular.PopularModelRoot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiConnection {
    private val apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNjAyZTg0YWI2ZDg2ZmIwOGZhNjVmNzBkMz" +
            "A1YWVhYyIsInN1YiI6IjY0OTJiZjlmZjlhYTQ3MDEwNjBlYWZiNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2Z" +
            "XJzaW9uIjoxfQ.ygWygBuiAWTaxFQrYBxAMdGfqq3KEPq43gDl4U89Jj4"

    val nowPlayingLiveData: MutableLiveData<NowPlayingModelRoot> = MutableLiveData()
    val popularLiveData: MutableLiveData<PopularModelRoot> = MutableLiveData()

    fun getNowPlayingList() {
        val nowPlayingService = ApiClient.getClient().create(INowPlayingData::class.java)
        val nowPlayingModelRootCall = nowPlayingService.createGet(apiKey)
        nowPlayingModelRootCall.enqueue(object : Callback<NowPlayingModelRoot> {
            override fun onResponse(call: Call<NowPlayingModelRoot>, response: Response<NowPlayingModelRoot>) {
                if (response.isSuccessful) {
                    nowPlayingLiveData.value = response.body() // Update the LiveData with the result
                } else {
                    nowPlayingLiveData.value = null // Update the LiveData with null if the response is not successful
                }
            }

            override fun onFailure(call: Call<NowPlayingModelRoot>, t: Throwable) {
                nowPlayingLiveData.value = null // Update the LiveData with null in case of failure
            }
        })
    }

    fun getPopularList() {
        val popularService = ApiClient.getClient().create(IPopularData::class.java)
        val popularModelRootCall = popularService.createGet(apiKey)
        popularModelRootCall.enqueue(object : Callback<PopularModelRoot> {
            override fun onResponse(
                call: Call<PopularModelRoot>,
                response: Response<PopularModelRoot>
            ) {
                if (response.isSuccessful) {
                    popularLiveData.value = response.body() // Update the LiveData with the result
                } else {
                    popularLiveData.value = null // Update the LiveData with null if the response is not successful
                }
            }

            override fun onFailure(call: Call<PopularModelRoot>, t: Throwable) {
                popularLiveData.value = null // Update the LiveData with null in case of failure
            }
        })
    }

}