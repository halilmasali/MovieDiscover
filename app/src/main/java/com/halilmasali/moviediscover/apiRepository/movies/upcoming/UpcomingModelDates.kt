package com.halilmasali.moviediscover.apiRepository.movies.upcoming

import com.google.gson.annotations.SerializedName

data class UpcomingModelDates(
    @SerializedName("maximum" ) var maximum : String? = null,
    @SerializedName("minimum" ) var minimum : String? = null

)
