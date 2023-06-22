package com.halilmasali.moviediscover.apiRepository.movies.nowPlaying

import com.google.gson.annotations.SerializedName

data class NowPlayingModelDates(
    @SerializedName("maximum" )
    var maximum : String? = null,
    @SerializedName("minimum" )
    var minimum : String? = null
)
