package com.halilmasali.moviediscover.apiRepository

import androidx.lifecycle.MutableLiveData
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.apiRepository.movies.INowPlayingData
import com.halilmasali.moviediscover.apiRepository.movies.MovieModelRoot
import com.halilmasali.moviediscover.apiRepository.movies.IPopularData
import com.halilmasali.moviediscover.apiRepository.movies.ISimilarMoviesData
import com.halilmasali.moviediscover.apiRepository.movies.ITopRatedData
import com.halilmasali.moviediscover.apiRepository.movies.IUpcomingData
import com.halilmasali.moviediscover.apiRepository.series.IAiringTodayData
import com.halilmasali.moviediscover.apiRepository.series.SeriesModelRoot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiConnection {

    val nowPlayingLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
    val popularLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
    val topRatedLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
    val upcomingLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
    val similarMoviesLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()

    val airingTodayLiveData: MutableLiveData<SeriesModelRoot> = MutableLiveData()

    fun getNowPlayingList() {
        val nowPlayingService = ApiClient.getClient(Constants.ApiBaseUrl).create(INowPlayingData::class.java)
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
        val popularService = ApiClient.getClient(Constants.ApiBaseUrl).create(IPopularData::class.java)
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
        val topRatedService = ApiClient.getClient(Constants.ApiBaseUrl).create(ITopRatedData::class.java)
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
        val upcomingService = ApiClient.getClient(Constants.ApiBaseUrl).create(IUpcomingData::class.java)
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

    fun getAiringTodayList() {
        val airingTodayService = ApiClient.getClient(Constants.ApiBaseUrl).create(IAiringTodayData::class.java)
        val airingTodayModelRootCall = airingTodayService.createGet(Constants.ApiKey)
        airingTodayModelRootCall.enqueue(object : Callback<SeriesModelRoot> {
            override fun onResponse(
                call: Call<SeriesModelRoot>,
                response: Response<SeriesModelRoot>
            ) {
                if (response.isSuccessful) {
                    airingTodayLiveData.value = response.body() // Update the LiveData with the result
                } else {
                    airingTodayLiveData.value = null // Update the LiveData with null if the response is not successful
                }
            }

            override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                airingTodayLiveData.value = null // Update the LiveData with null in case of failure
            }
        })
    }

    fun getSimilarMovies(movieId:Int){
        val similarMoviesService = ApiClient.getClient(Constants.ApiBaseUrl).create(ISimilarMoviesData::class.java)
        val similarMoviesModelRootCall = similarMoviesService.createGet(Constants.ApiKey,movieId)
        similarMoviesModelRootCall.enqueue(object : Callback<MovieModelRoot> {
            override fun onResponse(
                call: Call<MovieModelRoot>,
                response: Response<MovieModelRoot>
            ) {
                if (response.isSuccessful){
                    similarMoviesLiveData.value = response.body()// Update the LiveData with the result
                } else {
                    similarMoviesLiveData.value = null // Update the LiveData with null if the response is not successful
                }
            }

            override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                similarMoviesLiveData.value = null // Update the LiveData with null in case of failure
            }

        })
    }
}