package com.halilmasali.moviediscover.dataRepository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.room.Room
import com.halilmasali.moviediscover.dataRepository.apiRepository.ApiConnection
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelRoot
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelRoot
import com.halilmasali.moviediscover.dataRepository.roomRepository.LocalMovieData
import com.halilmasali.moviediscover.dataRepository.roomRepository.MovieDatabase
import kotlinx.coroutines.launch

class DataRepository(private val lifecycleOwner: LifecycleOwner,private val context: Context) {
    private val apiConnection = ApiConnection()
    fun series(){
        //region Series observers
        apiConnection.getSeriesAiringTodayList().observe(lifecycleOwner) { airingTodayList ->
            if (airingTodayList != null) {
                println("Airing Today: ${airingTodayList.results[0].name}")
                val database = Room.databaseBuilder(context,MovieDatabase::class.java,MovieDatabase.DATABASE_NAME).build()
                lifecycleOwner.lifecycleScope.launch {
                    airingTodayList.results[0].name?.let { insertDataToCache(it,database) }
                }
            }
            else
                println("Response null")
        }
        //endregion
    }
    suspend fun insertDataToCache(dataList: String, database: MovieDatabase) {
        val dataDao = database.dataDao()
        val data = LocalMovieData(1,dataList,1000)
        dataDao.insertData(data)
    }
}