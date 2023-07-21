package com.halilmasali.moviediscover.dataRepository.apiRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.dataRepository.DataResult
import com.halilmasali.moviediscover.dataRepository.apiRepository.creditsModel.CreditsModelRoot
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.ICreditsMoviesData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.INowPlayingData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelRoot
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.IPopularData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.ISimilarMoviesData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.ITopRatedData
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.IUpcomingData
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.IAiringTodayData
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.ICreditsSeriesData
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
    fun getMovieNowPlayingList(): LiveData<DataResult<MovieModelRoot>> {
        val nowPlayingLiveData: MutableLiveData<DataResult<MovieModelRoot>> = MutableLiveData()
        viewModelScope.launch {
            val nowPlayingService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(INowPlayingData::class.java)
            val nowPlayingModelRootCall = nowPlayingService.createGet(Constants.ApiKey)
            try {
                nowPlayingModelRootCall.enqueue(object : Callback<MovieModelRoot> {
                    override fun onResponse(call: Call<MovieModelRoot>, response: Response<MovieModelRoot>) {
                        if (response.isSuccessful) {
                            nowPlayingLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            nowPlayingLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                        nowPlayingLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                nowPlayingLiveData.value = DataResult(null,e)
            }
        }
        return nowPlayingLiveData
    }

    fun getMoviePopularList(): LiveData<DataResult<MovieModelRoot>> {
        val popularLiveData: MutableLiveData<DataResult<MovieModelRoot>> = MutableLiveData()
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
                            popularLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            popularLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                        popularLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                popularLiveData.value = DataResult(null,e)
            }
        }
        return popularLiveData
    }

    fun getMovieTopRatedList(): LiveData<DataResult<MovieModelRoot>> {
        val topRatedLiveData: MutableLiveData<DataResult<MovieModelRoot>> = MutableLiveData()
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
                            topRatedLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            topRatedLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                        topRatedLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                topRatedLiveData.value = DataResult(null,e)
            }
        }
        return topRatedLiveData
    }

    fun getMovieUpcomingList(): LiveData<DataResult<MovieModelRoot>> {
        val upcomingLiveData: MutableLiveData<DataResult<MovieModelRoot>> = MutableLiveData()
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
                            upcomingLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            upcomingLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                        upcomingLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                upcomingLiveData.value = DataResult(null,e)
            }
        }
        return upcomingLiveData
    }

    fun getMoviesSimilarList(movieId:Int): LiveData<DataResult<MovieModelRoot>> {
        val similarMoviesLiveData: MutableLiveData<DataResult<MovieModelRoot>> = MutableLiveData()
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
                            similarMoviesLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            similarMoviesLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful

                        }
                    }

                    override fun onFailure(call: Call<MovieModelRoot>, t: Throwable) {
                        similarMoviesLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                similarMoviesLiveData.value = DataResult(null,e)
            }
        }
        return similarMoviesLiveData
    }

    fun getMovieCredits(movieId:Int): LiveData<DataResult<CreditsModelRoot>> {
        val creditsLiveData: MutableLiveData<DataResult<CreditsModelRoot>> = MutableLiveData()
        viewModelScope.launch {
            val creditsService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(ICreditsMoviesData::class.java)
            val creditsModelRootCall = creditsService.createGet(Constants.ApiKey,movieId)
            try {
                creditsModelRootCall.enqueue(object : Callback<CreditsModelRoot> {
                    override fun onResponse(
                        call: Call<CreditsModelRoot>,
                        response: Response<CreditsModelRoot>
                    ) {
                        if (response.isSuccessful){
                            creditsLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            creditsLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<CreditsModelRoot>, t: Throwable) {
                        creditsLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                creditsLiveData.value = DataResult(null,e)
            }
        }
        return creditsLiveData
    }
    //endregion

    //region Series Requests
    fun getSeriesAiringTodayList(): LiveData<DataResult<SeriesModelRoot>> {
        val airingTodayLiveData: MutableLiveData<DataResult<SeriesModelRoot>> = MutableLiveData()
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
                            airingTodayLiveData.value = DataResult(response.body(),null)  // Update the LiveData with the result
                        } else {
                            airingTodayLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        airingTodayLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                airingTodayLiveData.value = DataResult(null,e)
            }
        }
        return airingTodayLiveData
    }

    fun getSeriesOnTheAirList():LiveData<DataResult<SeriesModelRoot>>{
        val onTheAirLiveData: MutableLiveData<DataResult<SeriesModelRoot>> = MutableLiveData()
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
                            onTheAirLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            onTheAirLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        onTheAirLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                onTheAirLiveData.value = DataResult(null,e)
            }
        }
        return onTheAirLiveData
    }

    fun getSeriesPopularList(): LiveData<DataResult<SeriesModelRoot>>{
        val popularLiveData: MutableLiveData<DataResult<SeriesModelRoot>> = MutableLiveData()
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
                            popularLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            popularLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        popularLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                popularLiveData.value = DataResult(null,e)
            }
        }
        return popularLiveData
    }

    fun getSeriesTopRatedList(): LiveData<DataResult<SeriesModelRoot>>{
        val topRatedLiveData: MutableLiveData<DataResult<SeriesModelRoot>> = MutableLiveData()
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
                            topRatedLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            topRatedLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        topRatedLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            } catch (e:Exception) {
                topRatedLiveData.value = DataResult(null,e)
            }
        }
        return topRatedLiveData
    }

    fun getSeriesSimilarList(seriesId:String): LiveData<DataResult<SeriesModelRoot>> {
        val similarSeriesLiveData: MutableLiveData<DataResult<SeriesModelRoot>> = MutableLiveData()
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
                            similarSeriesLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            similarSeriesLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<SeriesModelRoot>, t: Throwable) {
                        similarSeriesLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            }catch (e:Exception) {
                similarSeriesLiveData.value = DataResult(null,e)
            }
        }
        return similarSeriesLiveData
    }

    fun getSeriesCredits(seriesId:Int): LiveData<DataResult<CreditsModelRoot>> {
        val creditsLiveData: MutableLiveData<DataResult<CreditsModelRoot>> = MutableLiveData()
        viewModelScope.launch {
            val creditsService = ApiClient.getClient(Constants.ApiBaseUrl)
                .create(ICreditsSeriesData::class.java)
            val creditsModelRootCall = creditsService.createGet(Constants.ApiKey,seriesId)
            try {
                creditsModelRootCall.enqueue(object : Callback<CreditsModelRoot> {
                    override fun onResponse(
                        call: Call<CreditsModelRoot>,
                        response: Response<CreditsModelRoot>
                    ) {
                        if (response.isSuccessful){
                            creditsLiveData.value = DataResult(response.body(),null) // Update the LiveData with the result
                        } else {
                            creditsLiveData.value = DataResult(null,response.errorBody()) // Update the LiveData with null if the response is not successful
                        }
                    }

                    override fun onFailure(call: Call<CreditsModelRoot>, t: Throwable) {
                        creditsLiveData.value = DataResult(null,t) // Update the LiveData with null in case of failure
                    }
                })
            }catch (e:Exception) {
                creditsLiveData.value = DataResult(null,e)
            }
        }
        return creditsLiveData
    }
    //endregion
}