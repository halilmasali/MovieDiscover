package com.halilmasali.moviediscover.dataRepository.apiRepository

import okhttp3.ResponseBody
import org.json.JSONObject
import java.lang.Exception
import java.net.UnknownHostException

class ExceptionHandler {
    companion object {
        fun handleError(error: Any?): String {
            if (error == null)
                return ""
            return when (error) {
                is Exception -> {
                    if (error is UnknownHostException){
                        return "Please check your Internet connection."
                    } else
                        error.message ?: "Error"
                }

                is Throwable -> {
                    error.message ?: "Error"
                }

                is ResponseBody -> {
                    parseErrorMessage(error)
                }

                else -> "Error"
            }
        }

        private fun parseErrorMessage(responseBody: ResponseBody?): String {
            return try {
                val errorJson = responseBody?.string()
                val jsonObject = JSONObject(errorJson!!)
                jsonObject.optString("status_message", "Error")
            } catch (e: Exception) {
                "Error parsing response body"
            }
        }
    }
}