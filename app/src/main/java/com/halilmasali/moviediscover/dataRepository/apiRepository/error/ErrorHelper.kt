package com.halilmasali.moviediscover.dataRepository.apiRepository.error

import android.content.Context
import android.view.View
import com.halilmasali.moviediscover.R

class ErrorHelper {
    companion object {
        fun getErrorMessage(error: ErrorType, context: Context): String {
            return when (error) {
                ErrorType.NO_INTERNET_CONNECTION -> context.getString(R.string.check_internet_connection)
                ErrorType.API_ERROR -> context.getString(R.string.api_error_message)
                ErrorType.UNKNOWN_ERROR -> context.getString(R.string.unknown_error_message)
                else -> "No error"
            }
        }
        fun getErrorButtonVisibility(error: ErrorType): Int {
            return when (error) {
                ErrorType.NO_INTERNET_CONNECTION -> View.VISIBLE
                else -> View.INVISIBLE
            }
        }
        fun getErrorTextVisibility(error: ErrorType): Int {
            return when (error) {
                ErrorType.NO_ERROR -> View.INVISIBLE
                else -> View.VISIBLE
            }
        }
    }
}