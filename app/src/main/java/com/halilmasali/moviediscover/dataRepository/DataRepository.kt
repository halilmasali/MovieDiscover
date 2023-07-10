package com.halilmasali.moviediscover.dataRepository

import androidx.lifecycle.LifecycleOwner
import com.halilmasali.moviediscover.dataRepository.apiRepository.ApiConnection

class DataRepository(private val lifecycleOwner: LifecycleOwner) {
    private val apiConnection = ApiConnection()
    fun series(){
        //region Series observers
        apiConnection.getSeriesAiringTodayList().observe(lifecycleOwner) { airingTodayList ->
            if (airingTodayList != null)
                println("Airing Today: ${airingTodayList.results[0].name}")
            else
                println("Response null")
        }
        //endregion
    }
}