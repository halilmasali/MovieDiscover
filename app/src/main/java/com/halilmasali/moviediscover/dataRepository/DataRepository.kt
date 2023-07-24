package com.halilmasali.moviediscover.dataRepository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.dataRepository.apiRepository.ApiConnection
import com.halilmasali.moviediscover.dataRepository.apiRepository.creditsModel.CreditsModelCast
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelResults
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults
import com.halilmasali.moviediscover.dataRepository.roomRepository.LocalMoviesData
import com.halilmasali.moviediscover.dataRepository.roomRepository.LocalSeriesData
import com.halilmasali.moviediscover.dataRepository.roomRepository.RoomConnection

class DataRepository(private val lifecycleOwner: LifecycleOwner, context: Context) {
    private val api = ApiConnection()
    private val room = RoomConnection(context)

    // region Series Func
    fun getSeriesAiringToday(): LiveData<DataResult<ArrayList<SeriesModelResults>>> {
        val airingTodayLiveData: MutableLiveData<DataResult<ArrayList<SeriesModelResults>>> =
            MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalSeriesData>(Constants.SeriesAiringToday)
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    airingTodayLiveData.value = DataResult(cacheData.data, null)
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getSeriesAiringTodayList()
                        .observe(lifecycleOwner) { airingTodayList ->
                            if (airingTodayList.data?.results != null) {
                                val data = LocalSeriesData(
                                    category = Constants.SeriesAiringToday,
                                    expirationTime = Constants.ExpirationTime + System.currentTimeMillis(),
                                    data = airingTodayList.data?.results
                                )
                                room.insertDataToCache(data)
                                airingTodayLiveData.value =
                                    DataResult(airingTodayList.data!!.results, null)
                            } else {
                                airingTodayLiveData.value = DataResult(null, airingTodayList.error)
                                println("Response null")
                            }
                        }
                }
            }
        return airingTodayLiveData
    }

    fun getSeriesOnTheAir(): LiveData<DataResult<ArrayList<SeriesModelResults>>> {
        val onTheAirLiveData: MutableLiveData<DataResult<ArrayList<SeriesModelResults>>> =
            MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalSeriesData>(Constants.SeriesOnTheAir)
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    onTheAirLiveData.value = DataResult(cacheData.data, null)
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getSeriesOnTheAirList()
                        .observe(lifecycleOwner) { onTheAir ->
                            if (onTheAir.data?.results != null) {
                                val data = LocalSeriesData(
                                    category = Constants.SeriesOnTheAir,
                                    expirationTime = Constants.ExpirationTime + System.currentTimeMillis(),
                                    data = onTheAir.data?.results
                                )
                                room.insertDataToCache(data)
                                onTheAirLiveData.value = DataResult(onTheAir.data!!.results, null)
                            } else {
                                onTheAirLiveData.value = DataResult(null, onTheAir.error)
                                println("Response null")
                            }
                        }
                }
            }
        return onTheAirLiveData
    }

    fun getSeriesPopular(): LiveData<DataResult<ArrayList<SeriesModelResults>>> {
        val popularLiveData: MutableLiveData<DataResult<ArrayList<SeriesModelResults>>> =
            MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalSeriesData>(Constants.SeriesPopular)
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    popularLiveData.value = DataResult(cacheData.data, null)
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getSeriesPopularList()
                        .observe(lifecycleOwner) { popular ->
                            if (popular.data?.results != null) {
                                val data = LocalSeriesData(
                                    category = Constants.SeriesPopular,
                                    expirationTime = Constants.ExpirationTime + System.currentTimeMillis(),
                                    data = popular.data?.results
                                )
                                room.insertDataToCache(data)
                                popularLiveData.value = DataResult(popular.data!!.results, null)
                            } else {
                                popularLiveData.value = DataResult(null, popular.error)
                                println("Response null")
                            }
                        }
                }
            }
        return popularLiveData
    }

    fun getSeriesTopRated(): LiveData<DataResult<ArrayList<SeriesModelResults>>> {
        val topRatedLiveData: MutableLiveData<DataResult<ArrayList<SeriesModelResults>>> =
            MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalSeriesData>(Constants.SeriesTopRated)
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    topRatedLiveData.value = DataResult(cacheData.data, null)
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getSeriesTopRatedList()
                        .observe(lifecycleOwner) { topRated ->
                            if (topRated.data?.results != null) {
                                val data = LocalSeriesData(
                                    category = Constants.SeriesTopRated,
                                    expirationTime = Constants.ExpirationTime + System.currentTimeMillis(),
                                    data = topRated.data?.results
                                )
                                room.insertDataToCache(data)
                                topRatedLiveData.value = DataResult(topRated.data!!.results, null)
                            } else {
                                topRatedLiveData.value = DataResult(null, topRated.error)
                                println("Response null")
                            }
                        }
                }
            }
        return topRatedLiveData
    }

    fun getSeriesSimilar(seriesId: Int): LiveData<DataResult<ArrayList<SeriesModelResults>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<SeriesModelResults>>> = MutableLiveData()
        api.getSeriesSimilarList(seriesId.toString()).observe(lifecycleOwner) { similar ->
            if (similar.data?.results != null) {
                liveData.value = DataResult(similar.data!!.results, null)
            } else {
                liveData.value = DataResult(null, similar.error)
                println("Response null")
            }
        }
        return liveData
    }

    fun getSeriesCast(seriesId: Int): LiveData<DataResult<ArrayList<CreditsModelCast>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<CreditsModelCast>>> = MutableLiveData()
        api.getSeriesCredits(seriesId).observe(lifecycleOwner) { credits ->
            if (credits.data?.cast != null) {
                liveData.value = DataResult(credits.data!!.cast, null)
            } else {
                liveData.value = DataResult(null, credits.error)
                println("Response null")
            }
        }
        return liveData
    }
    // endregion

    // region Movies Func
    fun getMovieNowPlaying(): LiveData<DataResult<ArrayList<MovieModelResults>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<MovieModelResults>>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>(Constants.MovieNowPlaying)
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    liveData.value = DataResult(cacheData.data, null)
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getMovieNowPlayingList()
                        .observe(lifecycleOwner) { item ->
                            if (item.data?.results != null) {
                                val data = LocalMoviesData(
                                    category = Constants.MovieNowPlaying,
                                    expirationTime = Constants.ExpirationTime + System.currentTimeMillis(),
                                    data = item.data?.results
                                )
                                room.insertDataToCache(data)
                                liveData.value = DataResult(item.data!!.results, null)
                            } else {
                                liveData.value = DataResult(null, item.error)
                                println("Response null")
                            }
                        }
                }
            }
        return liveData
    }

    fun getMoviePopular(): LiveData<DataResult<ArrayList<MovieModelResults>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<MovieModelResults>>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>(Constants.MoviePopular)
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    liveData.value = DataResult(cacheData.data, null)
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getMoviePopularList()
                        .observe(lifecycleOwner) { item ->
                            if (item.data?.results != null) {
                                val data = LocalMoviesData(
                                    category = Constants.MoviePopular,
                                    expirationTime = Constants.ExpirationTime + System.currentTimeMillis(),
                                    data = item.data?.results
                                )
                                room.insertDataToCache(data)
                                liveData.value = DataResult(item.data!!.results, null)
                            } else {
                                liveData.value = DataResult(null, item.error)
                                println("Response null")
                            }
                        }
                }
            }
        return liveData
    }

    fun getMovieTopRated(): LiveData<DataResult<ArrayList<MovieModelResults>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<MovieModelResults>>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>(Constants.MovieTopRated)
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    liveData.value = DataResult(cacheData.data, null)
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getMovieTopRatedList()
                        .observe(lifecycleOwner) { item ->
                            if (item.data?.results != null) {
                                val data = LocalMoviesData(
                                    category = Constants.MovieTopRated,
                                    expirationTime = Constants.ExpirationTime + System.currentTimeMillis(),
                                    data = item.data?.results
                                )
                                room.insertDataToCache(data)
                                liveData.value = DataResult(item.data!!.results, null)
                            } else {
                                liveData.value = DataResult(null, item.error)
                                println("Response null")
                            }
                        }
                }
            }
        return liveData
    }

    fun getMovieUpcoming(): LiveData<DataResult<ArrayList<MovieModelResults>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<MovieModelResults>>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>(Constants.MovieUpcoming)
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    liveData.value = DataResult(cacheData.data, null)
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getMovieUpcomingList()
                        .observe(lifecycleOwner) { item ->
                            if (item.data?.results != null) {
                                val data = LocalMoviesData(
                                    category = Constants.MovieUpcoming,
                                    expirationTime = Constants.ExpirationTime + System.currentTimeMillis(),
                                    data = item.data?.results
                                )
                                room.insertDataToCache(data)
                                liveData.value = DataResult(item.data!!.results, null)
                            } else {
                                liveData.value = DataResult(null, item.error)
                                println("Response null")
                            }
                        }
                }
            }
        return liveData
    }

    fun getMoviesSimilar(movieId: Int): LiveData<DataResult<ArrayList<MovieModelResults>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<MovieModelResults>>> = MutableLiveData()
        api.getMoviesSimilarList(movieId).observe(lifecycleOwner) { similar ->
            if (similar.data?.results != null) {
                liveData.value = DataResult(similar.data!!.results, null)
            } else {
                liveData.value = DataResult(null, similar.error)
                println("Response null")
            }
        }
        return liveData
    }

    fun getMovieCast(movieId: Int): LiveData<DataResult<ArrayList<CreditsModelCast>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<CreditsModelCast>>> = MutableLiveData()
        api.getMovieCredits(movieId).observe(lifecycleOwner) { credits ->
            if (credits.data?.cast != null) {
                liveData.value = DataResult(credits.data!!.cast, null)
            } else {
                liveData.value = DataResult(null, credits.error)
                println("Response null")
            }
        }
        return liveData
    }
    // endregion
}