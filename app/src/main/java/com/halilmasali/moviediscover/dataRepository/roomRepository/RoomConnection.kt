package com.halilmasali.moviediscover.dataRepository.roomRepository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomConnection(context: Context) {

    val database = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, MovieDatabase.DATABASE_NAME
    ).build()

    fun <T> insertDataToCache(data: T) {
        CoroutineScope(Dispatchers.IO).launch {
            val dataDao = database.dataDao()
            when (data) {
                is LocalSeriesData -> dataDao.insertSeriesData(data)
                is LocalMoviesData -> dataDao.insertMoviesData(data)
            }
        }
    }

    inline fun <reified T> getDataFromCacheByCategory(category: String): LiveData<T> {
        val liveData: MutableLiveData<T> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val dataDao = database.dataDao()
            val data: T? = when {
                T::class == LocalSeriesData::class -> dataDao.getSeriesDataByCategory(category) as? T
                T::class == LocalMoviesData::class -> dataDao.getMoviesDataByCategory(category) as? T
                else -> null
            }
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

    fun checkExpirationTime(data: Any?): Boolean {
        if (data == null)
            return false
        val currentTimeInMillis = System.currentTimeMillis()
        return when (data) {
            is LocalSeriesData -> data.expirationTime > currentTimeInMillis
            is LocalMoviesData -> data.expirationTime > currentTimeInMillis
            else -> false
        }
    }
}