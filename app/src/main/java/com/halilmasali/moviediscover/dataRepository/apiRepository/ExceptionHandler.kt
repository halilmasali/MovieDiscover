package com.halilmasali.moviediscover.dataRepository.apiRepository

import okhttp3.ResponseBody
import org.json.JSONObject
import java.lang.Exception
import java.net.UnknownHostException

class ExceptionHandler {
    companion object {
        fun handleError(error: Any?): ErrorType {
            if (error == null)
                return ErrorType.NO_ERROR
            return when (error) {
                is Exception -> {
                    if (error is UnknownHostException) {
                        ErrorType.NO_INTERNET_CONNECTION
                    } else
                        ErrorType.UNKNOWN_ERROR
                }

                is Throwable -> {
                    ErrorType.UNKNOWN_ERROR
                }

                is ResponseBody -> {
                    parseErrorMessage(error)
                }

                else -> ErrorType.UNKNOWN_ERROR
            }
        }

        private fun parseErrorMessage(responseBody: ResponseBody?): ErrorType {
            return try {
                val errorJson = responseBody?.string()
                val jsonObject = JSONObject(errorJson!!)
                val statusMessage = jsonObject.optString("status_message", "Error")
                if (statusMessage != "Error") {
                    ErrorType.API_ERROR
                } else {
                    ErrorType.UNKNOWN_ERROR
                }
            } catch (e: Exception) {
                ErrorType.API_ERROR
            }
        }
    }
}