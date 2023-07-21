package com.halilmasali.moviediscover.dataRepository.apiRepository.creditsModel

import com.google.gson.annotations.SerializedName

data class CreditsModelRoot(
    @SerializedName("id"   ) var id   : Int?            = null,
    @SerializedName("cast" ) var cast : ArrayList<CreditsModelCast> = arrayListOf(),
    @SerializedName("crew" ) var crew : ArrayList<Any> = arrayListOf()
)
