package com.halilmasali.moviediscover.dataRepository.apiRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.INowPlayingData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelRoot
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.IPopularData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.ISimilarMoviesData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.ITopRatedData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.IUpcomingData
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.IAiringTodayData
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.IOnTheAirData
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.ISeriesPopularData
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.ISeriesTopRatedData
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.ISimilarSeriesData
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelRoot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiConnection: ViewModel() {

    //region Movies Requests
    fun getMovieNowPlayingList(): LiveData<MovieModelRoot> {
        val nowPlayingLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val nowPlayingService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(INowPlayingData::class.java)
            val nowPlayingModelRootCall = nowPlayingService.createGet(Constants.ApiKey)
            try {
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
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return nowPlayingLiveData
    }

    fun getMoviePopularList(): LiveData<MovieModelRoot> {
        val popularLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val popularService = ApiClient.getClient(Constants.ApiBaseUrl).create(IPopularData::class.java)
            val popularModelRootCall = popularService.createGet(Constants.ApiKey)
            try {
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
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return popularLiveData
    }

    fun getMovieTopRatedList(): LiveData<MovieModelRoot> {
        val topRatedLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val topRatedService = ApiClient.getClient(Constants.ApiBaseUrl).create(ITopRatedData::class.java)
            val topRatedModelRootCall = topRatedService.createGet(Constants.ApiKey)
            try {
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
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return topRatedLiveData
    }

    fun getMovieUpcomingList(): LiveData<MovieModelRoot> {
        val upcomingLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val upcomingService = ApiClient.getClient(Constants.ApiBaseUrl).create(IUpcomingData::class.java)
            val upcomingModelRootCall = upcomingService.createGet(Constants.ApiKey)
            try {
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
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return upcomingLiveData
    }

    fun getMoviesSimilarList(movieId:Int): LiveData<MovieModelRoot> {
        val similarMoviesLiveData: MutableLiveData<MovieModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val similarMoviesService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(ISimilarMoviesData::class.java)
            val similarMoviesModelRootCall = similarMoviesService.createGet(Constants.ApiKey,movieId)
            try {
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
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return similarMoviesLiveData
    }
    //endregion

    //region Series Requests
    fun getSeriesAiringTodayList(): LiveData<SeriesModelRoot> {
        val airingTodayLiveData: MutableLiveData<SeriesModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val airingTodayService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(IAiringTodayData::class.java)
            val airingTodayModelRootCall = airingTodayService.createGet(Constants.ApiKey)
            try {
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
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return airingTodayLiveData
    }

    fun getSeriesOnTheAirList(): LiveData<SeriesModelRoot>{
        val onTheAirLiveData: MutableLiveData<SeriesModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val onTheAirService = ApiClient.getClient(Constants.ApiBaseUrl).create(IOnTheAirData::class.java)
            val onTheAirModelRootCall = onTheAirService.createGet(Constants.ApiKey)
            try {
                onTheAirModelRootCall.enqueue(object : Callback<SeriesModelRoot> {
                    override fun onResponse(
                        call: Call<SeriesModelRoot>,
                        response: Response<SeriesModelRoot>
                    ) {
                        if (response.isSuccessful) {
                            onTheAirLiveData.value = response.body() // Update the LiveData with the result
                        } else {
                            onTheAirLiveData.value = null // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        onTheAirLiveData.value = null // Update the LiveData with null in case of failure
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return onTheAirLiveData
    }

    fun getSeriesPopularList(): LiveData<SeriesModelRoot>{
        val popularLiveData: MutableLiveData<SeriesModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val popularService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(ISeriesPopularData::class.java)
            val popularModelRootCall = popularService.createGet(Constants.ApiKey)
            try {
                popularModelRootCall.enqueue(object : Callback<SeriesModelRoot> {
                    override fun onResponse(
                        call: Call<SeriesModelRoot>,
                        response: Response<SeriesModelRoot>
                    ) {
                        if (response.isSuccessful) {
                            popularLiveData.value = response.body() // Update the LiveData with the result
                        } else {
                            popularLiveData.value = null // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        popularLiveData.value = null // Update the LiveData with null in case of failure
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return popularLiveData
    }

    fun getSeriesTopRatedList(): LiveData<SeriesModelRoot>{
        val topRatedLiveData: MutableLiveData<SeriesModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val topRatedService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(ISeriesTopRatedData::class.java)
            val topRatedModelRootCall = topRatedService.createGet(Constants.ApiKey)
            try {
                topRatedModelRootCall.enqueue(object : Callback<SeriesModelRoot> {
                    override fun onResponse(
                        call: Call<SeriesModelRoot>,
                        response: Response<SeriesModelRoot>
                    ) {
                        if (response.isSuccessful) {
                            topRatedLiveData.value = response.body() // Update the LiveData with the result
                        } else {
                            topRatedLiveData.value = null // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        topRatedLiveData.value = null // Update the LiveData with null in case of failure
                        ExceptionHandler.handleFailure(t)
                    }
                })
            } catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return topRatedLiveData
    }

    fun getSeriesSimilarList(seriesId:String): LiveData<SeriesModelRoot> {
        val similarSeriesLiveData: MutableLiveData<SeriesModelRoot> = MutableLiveData()
        viewModelScope.launch {
            val similarSeriesService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(ISimilarSeriesData::class.java)
            val similarSeriesModelRootCall = similarSeriesService.createGet(Constants.ApiKey,seriesId)
            try {
                similarSeriesModelRootCall.enqueue(object : Callback<SeriesModelRoot> {
                    override fun onResponse(
                        call: Call<SeriesModelRoot>,
                        response: Response<SeriesModelRoot>
                    ) {
                        if (response.isSuccessful){
                            similarSeriesLiveData.value = response.body()// Update the LiveData with the result
                        } else {
                            similarSeriesLiveData.value = null // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        similarSeriesLiveData.value = null // Update the LiveData with null in case of failure
                        ExceptionHandler.handleFailure(t)
                    }
                })
            }catch (e:Exception) {
                ExceptionHandler.handleException(e)
            }
        }
        return similarSeriesLiveData
    }
    //endregion
}