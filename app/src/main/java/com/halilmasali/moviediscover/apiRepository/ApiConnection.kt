package com.halilmasali.moviediscover.apiRepository

import androidx.lifecycle.MutableLiveData
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.apiRepository.movies.INowPlayingData
import com.halilmasali.moviediscover.apiRepository.movies.MovieModelRoot
import com.halilmasali.moviediscover.apiRepository.movies.IPopularData
import com.halilmasali.moviediscover.apiRepository.movies.ITopRatedData
import com.halilmasali.moviediscover.apiRepository.movies.IUpcomingData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiConnection {

    val nowPlayingLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
    val popularLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
    val topRatedLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
    val upcomingLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()

    fun getNowPlayingList() {
        val nowPlayingService = ApiClient.getClient().create(INowPlayingData::class.java)
        val nowPlayingModelRootCall = nowPlayingService.createGet(Constants.ApiKey)
        nowPlayingModelRootCall.enqueue(object : Callback<MovieModelRoot> {
            override fun onResponse(call: Call<MovieModelRoot>, response: Response<MovieModelRoot>) {
                if (response.isSuccessful) {
                    nowPlayingLiveData.value = response.body() // Update the LiveData with the result
                } else {
                    nowPlayingLiveData.value = null // Update the LiveData with null if the response is not successful
                }
            }

            override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                nowPlayingLiveData.value = null // Update the LiveData with null in case of failure
            }
        })
    }

    fun getPopularList() {
        val popularService = ApiClient.getClient().create(IPopularData::class.java)
        val popularModelRootCall = popularService.createGet(Constants.ApiKey)
        popularModelRootCall.enqueue(object : Callback<MovieModelRoot> {
            override fun onResponse(
                call: Call<MovieModelRoot>,
                response: Response<MovieModelRoot>
            ) {
                if (response.isSuccessful) {
                    popularLiveData.value = response.body() // Update the LiveData with the result
                } else {
                    popularLiveData.value = null // Update the LiveData with null if the response is not successful
                }
            }

            override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                popularLiveData.value = null // Update the LiveData with null in case of failure
            }
        })
    }

    fun getTopRatedList() {
        val topRatedService = ApiClient.getClient().create(ITopRatedData::class.java)
        val topRatedModelRootCall = topRatedService.createGet(Constants.ApiKey)
        topRatedModelRootCall.enqueue(object : Callback<MovieModelRoot> {
            override fun onResponse(
                call: Call<MovieModelRoot>,
                response: Response<MovieModelRoot>
            ) {
                if (response.isSuccessful) {
                    topRatedLiveData.value = response.body() // Update the LiveData with the result
                } else {
                    topRatedLiveData.value = null // Update the LiveData with null if the response is not successful
                }
            }

            override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                topRatedLiveData.value = null // Update the LiveData with null in case of failure
            }

        })
    }
    fun getUpcomingList() {
        val upcomingService = ApiClient.getClient().create(IUpcomingData::class.java)
        val upcomingModelRootCall = upcomingService.createGet(Constants.ApiKey)
        upcomingModelRootCall.enqueue(object : Callback<MovieModelRoot> {
            override fun onResponse(
                call: Call<MovieModelRoot>,
                response: Response<MovieModelRoot>
            ) {
                if (response.isSuccessful) {
                    upcomingLiveData.value = response.body() // Update the LiveData with the result
                } else {
                    upcomingLiveData.value = null // Update the LiveData with null if the response is not successful
                }
            }

            override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                upcomingLiveData.value = null // Update the LiveData with null in case of failure
            }
        })
    }
}