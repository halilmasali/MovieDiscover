package com.halilmasali.moviediscover.apiRepository.movies.popular

import com.google.gson.annotations.SerializedName

data class PopularModelRoot(
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<PopularModelResults> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)
