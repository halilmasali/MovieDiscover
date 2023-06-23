package com.halilmasali.moviediscover.apiRepository.movies

import com.google.gson.annotations.SerializedName

data class MovieModelDates(
    @SerializedName("maximum" )
    var maximum : String? = null,
    @SerializedName("minimum" )
    var minimum : String? = null
)
