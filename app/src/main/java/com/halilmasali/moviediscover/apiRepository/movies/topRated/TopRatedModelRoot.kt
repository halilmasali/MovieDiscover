package com.halilmasali.moviediscover.apiRepository.movies.topRated

import com.google.gson.annotations.SerializedName

data class TopRatedModelRoot(
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<TopRatedModelResults> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)
