package com.halilmasali.moviediscover.dataRepository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.dataRepository.apiRepository.ApiConnection
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults
import com.halilmasali.moviediscover.dataRepository.roomRepository.LocalMoviesData
import com.halilmasali.moviediscover.dataRepository.roomRepository.LocalSeriesData
import com.halilmasali.moviediscover.dataRepository.roomRepository.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepository(private val lifecycleOwner: LifecycleOwner, context: Context) {
    private val apiConnection = ApiConnection()
    private val database =
        Room.databaseBuilder(context, MovieDatabase::class.java, MovieDatabase.DATABASE_NAME)
            .build()

    //region Series Func
    fun getSeriesAiringToday(): LiveData<ArrayList<SeriesModelResults>> {
        val airingTodayLiveData: MutableLiveData<ArrayList<SeriesModelResults>> = MutableLiveData()
        // Used if cache data exists and has not expired.
        getDataFromCacheByCategory<LocalSeriesData>("SeriesAiringToday")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    airingTodayLiveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    apiConnection.getSeriesAiringTodayList()
                        .observe(lifecycleOwner) { airingTodayList ->
                            if (airingTodayList != null) {
                                val data = LocalSeriesData(
                                    1,
                                    "SeriesAiringToday",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    airingTodayList.results
                                )
                                insertDataToCache(data, database)
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
        getDataFromCacheByCategory<LocalSeriesData>("SeriesOnTheAir")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    onTheAirLiveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    apiConnection.getSeriesOnTheAirList()
                        .observe(lifecycleOwner) { onTheAir ->
                            if (onTheAir != null) {
                                val data = LocalSeriesData(
                                    1,
                                    "SeriesOnTheAir",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    onTheAir.results
                                )
                                insertDataToCache(data, database)
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
        getDataFromCacheByCategory<LocalSeriesData>("SeriesPopular")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    popularLiveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    apiConnection.getSeriesPopularList()
                        .observe(lifecycleOwner) { popular ->
                            if (popular != null) {
                                val data = LocalSeriesData(
                                    1,
                                    "SeriesPopular",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    popular.results
                                )
                                insertDataToCache(data, database)
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
        getDataFromCacheByCategory<LocalSeriesData>("SeriesTopRated")
            .observe(lifecycleOwner) { cacheData ->
                if (cacheData != null) {
                    topRatedLiveData.value = cacheData.data
                    println("Cache value")
                } else {
                    //If the cache data has expired or null,
                    // data is taken from the api and saves it for the cache.
                    apiConnection.getSeriesTopRatedList()
                        .observe(lifecycleOwner) { topRated ->
                            if (topRated != null) {
                                val data = LocalSeriesData(
                                    1,
                                    "SeriesTopRated",
                                    Constants.ExpirationTime + System.currentTimeMillis(),
                                    topRated.results
                                )
                                insertDataToCache(data, database)
                                topRatedLiveData.value = topRated.results
                            } else
                                println("Response null")
                        }
                }
            }
        return topRatedLiveData
    }

    fun getSeriesSimilar(seriesId: String) {
        apiConnection.getSeriesSimilarList(seriesId).observe(lifecycleOwner) { similar ->
            if (similar != null) {
                println("Series Similar: ${similar.results[0].name}")
                val data = LocalSeriesData(
                    4,
                    "SeriesSimilar",
                    Constants.ExpirationTime,
                    similar.results
                )
                insertDataToCache(data, database)
            } else
                println("Response null")
        }
    }
    // endregion

    private fun insertDataToCache(data: LocalSeriesData, database: MovieDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            val dataDao = database.dataDao()
            dataDao.insertSeriesData(data)
        }
    }

    fun getDataFromCache() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataDao = database.dataDao()
            val data = dataDao.getAllSeriesData()
            println("Data from cache: ${data}")
        }
    }

    private fun <T> getDataFromCacheByCategory(category: String): LiveData<T> {
        val liveData: MutableLiveData<T> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val dataDao = database.dataDao()
            val data = dataDao.getSeriesDataByCategory(category)
            if (checkExpirationTime(data))
                liveData.postValue(data as T)
            else
                liveData.postValue(null)
            println("Data from cache: $data")
        }.invokeOnCompletion {
            return@invokeOnCompletion
        }
        return liveData
    }

    private fun checkExpirationTime(data: Any?): Boolean {
        if (data == null)
            return false
        val currentTimeInMillis = System.currentTimeMillis()
        return when (data) {
            is LocalSeriesData -> {
                data.expirationTime > currentTimeInMillis
            }
            is LocalMoviesData -> {
                data.expirationTime > currentTimeInMillis
            }
            else -> {
                false
            }
        }
    }
}