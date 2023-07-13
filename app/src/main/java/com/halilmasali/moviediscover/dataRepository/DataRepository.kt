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

    //region Series Func
    fun getSeriesAiringToday(): LiveData<ArrayList<SeriesModelResults>> {
        val airingTodayLiveData: MutableLiveData<ArrayList<SeriesModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalSeriesData>("SeriesAiringToday")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    airingTodayLiveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getSeriesAiringTodayList()
                        .observe(lifecycleOwner) { airingTodayList ->
                            if (airingTodayList != null) {
                                val data = LocalSeriesData(
                                    1,
                                    "SeriesAiringToday",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    airingTodayList.results
                                )
                                room.insertDataToCache(data)
                                airingTodayLiveData.value = airingTodayList.results
                            } else
                                println("Response null")
                        }
                }
            }
        return airingTodayLiveData
    }

    fun getSeriesOnTheAir(): LiveData<ArrayList<SeriesModelResults>> {
        val onTheAirLiveData: MutableLiveData<ArrayList<SeriesModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalSeriesData>("SeriesOnTheAir")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    onTheAirLiveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getSeriesOnTheAirList()
                        .observe(lifecycleOwner) { onTheAir ->
                            if (onTheAir != null) {
                                val data = LocalSeriesData(
                                    2,
                                    "SeriesOnTheAir",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    onTheAir.results
                                )
                                room.insertDataToCache(data)
                                onTheAirLiveData.value = onTheAir.results
                            } else
                                println("Response null")
                        }
                }
            }
        return onTheAirLiveData
    }

    fun getSeriesPopular(): LiveData<ArrayList<SeriesModelResults>> {
        val popularLiveData: MutableLiveData<ArrayList<SeriesModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalSeriesData>("SeriesPopular")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    popularLiveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getSeriesPopularList()
                        .observe(lifecycleOwner) { popular ->
                            if (popular != null) {
                                val data = LocalSeriesData(
                                    3,
                                    "SeriesPopular",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    popular.results
                                )
                                room.insertDataToCache(data)
                                popularLiveData.value = popular.results
                            } else
                                println("Response null")
                        }
                }
            }
        return popularLiveData
    }

    fun getSeriesTopRated(): LiveData<ArrayList<SeriesModelResults>> {
        val topRatedLiveData: MutableLiveData<ArrayList<SeriesModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalSeriesData>("SeriesTopRated")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    topRatedLiveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getSeriesTopRatedList()
                        .observe(lifecycleOwner) { topRated ->
                            if (topRated != null) {
                                val data = LocalSeriesData(
                                    4,
                                    "SeriesTopRated",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    topRated.results
                                )
                                room.insertDataToCache(data)
                                topRatedLiveData.value = topRated.results
                            } else
                                println("Response null")
                        }
                }
            }
        return topRatedLiveData
    }

    // TODO Series Similar data is unique to selected content, so may be create new table.
    fun getSeriesSimilar(seriesId: String) {
        api.getSeriesSimilarList(seriesId).observe(lifecycleOwner) { similar ->
            if (similar != null) {
                println("Series Similar: ${similar.results[0].name}")
                val data = LocalSeriesData(
                    5,
                    "SeriesSimilar",
                    Constants.ExpirationTime,
                    similar.results
                )
                room.insertDataToCache(data)
            } else
                println("Response null")
        }
    }
    // endregion

    // region Movies Func
    fun getMovieNowPlaying(): LiveData<ArrayList<MovieModelResults>> {
        val liveData: MutableLiveData<ArrayList<MovieModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>("MovieNowPlaying")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    liveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getMovieNowPlayingList()
                        .observe(lifecycleOwner) { item ->
                            if (item != null) {
                                val data = LocalMoviesData(
                                    1,
                                    "MovieNowPlaying",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    item.results
                                )
                                room.insertDataToCache(data)
                                liveData.value = item.results
                            } else
                                println("Response null")
                        }
                }
            }
        return liveData
    }

    fun getMoviePopular(): LiveData<ArrayList<MovieModelResults>> {
        val liveData: MutableLiveData<ArrayList<MovieModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>("MoviePopular")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    liveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getMoviePopularList()
                        .observe(lifecycleOwner) { item ->
                            if (item != null) {
                                val data = LocalMoviesData(
                                    2,
                                    "MoviePopular",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    item.results
                                )
                                room.insertDataToCache(data)
                                liveData.value = item.results
                            } else
                                println("Response null")
                        }
                }
            }
        return liveData
    }

    fun getMovieTopRated(): LiveData<ArrayList<MovieModelResults>> {
        val liveData: MutableLiveData<ArrayList<MovieModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>("MovieTopRated")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    liveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getMovieTopRatedList()
                        .observe(lifecycleOwner) { item ->
                            if (item != null) {
                                val data = LocalMoviesData(
                                    3,
                                    "MovieTopRated",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    item.results
                                )
                                room.insertDataToCache(data)
                                liveData.value = item.results
                            } else
                                println("Response null")
                        }
                }
            }
        return liveData
    }

    fun getMovieUpcoming(): LiveData<ArrayList<MovieModelResults>> {
        val liveData: MutableLiveData<ArrayList<MovieModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        room.getDataFromCacheByCategory<LocalMoviesData>("MovieUpcoming")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    liveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    api.getMovieUpcomingList()
                        .observe(lifecycleOwner) { item ->
                            if (item != null) {
                                val data = LocalMoviesData(
                                    4,
                                    "MovieUpcoming",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    item.results
                                )
                                room.insertDataToCache(data)
                                liveData.value = item.results
                            } else
                                println("Response null")
                        }
                }
            }
        return liveData
    }
    // endregion
}