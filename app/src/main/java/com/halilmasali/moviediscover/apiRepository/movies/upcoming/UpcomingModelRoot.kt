package com.halilmasali.moviediscover.apiRepository.movies.upcoming

import com.google.gson.annotations.SerializedName

data class UpcomingModelRoot(
    @SerializedName("dates"         ) var dates        : UpcomingModelDates?= UpcomingModelDates(),
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<UpcomingModelResults> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)
