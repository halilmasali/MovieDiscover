package com.halilmasali.moviediscover.dataRepository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.dataRepository.apiRepository.ApiConnection
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
        room.getDataFromCacheByCategory<LocalSeriesData>("SeriesAiringToday")
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
                                    1,
                                    "SeriesAiringToday",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    airingTodayList.data?.results
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
        room.getDataFromCacheByCategory<LocalSeriesData>("SeriesOnTheAir")
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
                                    2,
                                    "SeriesOnTheAir",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    onTheAir.data?.results
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
        room.getDataFromCacheByCategory<LocalSeriesData>("SeriesPopular")
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
                                    3,
                                    "SeriesPopular",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    popular.data?.results
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
        room.getDataFromCacheByCategory<LocalSeriesData>("SeriesTopRated")
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
                                    4,
                                    "SeriesTopRated",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    topRated.data?.results
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

    // TODO Series Similar data is unique to selected content, so may be create new table.
    fun getSeriesSimilar(seriesId: String) {
        api.getSeriesSimilarList(seriesId).observe(lifecycleOwner) { similar ->
            if (similar != null) {
                println("Series Similar: ${similar.data?.results?.get(0)?.name}")
                val data = LocalSeriesData(
                    5,
                    "SeriesSimilar",
                    Constants.ExpirationTime,
                    similar.data?.results
                )
                room.insertDataToCache(data)
            } else
                println("Response null")
        }
    }
    // endregion

    // region Movies Func
    fun getMovieNowPlaying(): LiveData<DataResult<ArrayList<MovieModelResults>>> {
        val liveData: MutableLiveData<DataResult<ArrayList<MovieModelResults>>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>("MovieNowPlaying")
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
                                    1,
                                    "MovieNowPlaying",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    item.data?.results
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
        room.getDataFromCacheByCategory<LocalMoviesData>("MoviePopular")
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
                                    2,
                                    "MoviePopular",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    item.data?.results
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
        room.getDataFromCacheByCategory<LocalMoviesData>("MovieTopRated")
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
                                    3,
                                    "MovieTopRated",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    item.data?.results
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
        room.getDataFromCacheByCategory<LocalMoviesData>("MovieUpcoming")
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
                                    4,
                                    "MovieUpcoming",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    item.data?.results
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
    // endregion
}