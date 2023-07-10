package com.halilmasali.moviediscover.dataRepository.apiRepository.series

import com.google.gson.annotations.SerializedName

data class SeriesModelRoot (
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<SeriesModelResults> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)