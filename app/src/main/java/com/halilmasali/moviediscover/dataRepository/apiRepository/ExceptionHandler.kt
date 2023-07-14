package com.halilmasali.moviediscover.dataRepository.apiRepository

import android.util.Log
import org.json.JSONException
import java.io.IOException
import java.lang.Exception

class ExceptionHandler {
    companion object {
        fun handleException(exception: Exception) {
            when (exception) {
                is IOException -> {
                    //File execute problem
                    Log.e("ExceptionHandler", "IOException occurred: ${exception.message}")
                }
                is JSONException -> {
                    // JSON error
                    Log.e("ExceptionHandler", "JSONException occurred: ${exception.message}")
                }
                else -> {
                    // Other unexpected errors
                    Log.e("ExceptionHandler", "Exception occurred: ${exception.message}")
                }
            }
        }
        fun handleFailure(throwable: Throwable){
            when (throwable) {
                is IOException ->{
                    // Network error
                    Log.e("ExceptionHandler", "IOException occurred: ${throwable.message}")
                }
                else -> {
                    // Other unexpected errors
                    Log.e("ExceptionHandler", "Exception occurred: ${throwable.message}")
                }
            }
        }
    }
}