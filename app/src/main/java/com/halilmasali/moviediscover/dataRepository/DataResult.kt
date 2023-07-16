package com.halilmasali.moviediscover.dataRepository

data class DataResult<out T>(var data: @UnsafeVariance T?, val error: Any?)