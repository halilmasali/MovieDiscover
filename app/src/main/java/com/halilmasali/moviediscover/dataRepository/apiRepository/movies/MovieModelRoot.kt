package com.halilmasali.moviediscover.dataRepository.apiRepository.movies

import com.google.gson.annotations.SerializedName

data class MovieModelRoot(
    @SerializedName("dates"         ) var dates        : MovieModelDates?             = MovieModelDates(),
    @SerializedName("page"          ) var page         : Int?                              = null,
    @SerializedName("results"       ) var results      : ArrayList<MovieModelResults> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?                              = null,
    @SerializedName("total_results" ) var totalResults : Int?                              = null
)
