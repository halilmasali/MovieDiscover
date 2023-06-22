package com.halilmasali.moviediscover.apiRepository.movies.nowPlaying

import com.google.gson.annotations.SerializedName

data class NowPlayingModelRoot(
    @SerializedName("dates"         ) var dates        : NowPlayingModelDates?             = NowPlayingModelDates(),
    @SerializedName("page"          ) var page         : Int?                              = null,
    @SerializedName("results"       ) var results      : ArrayList<NowPlayingModelResults> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?                              = null,
    @SerializedName("total_results" ) var totalResults : Int?                              = null
)
