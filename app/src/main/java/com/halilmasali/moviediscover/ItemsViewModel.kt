package com.halilmasali.moviediscover

data class ItemsViewModel (
    var title: String? = null,
    var error: Any? = null,
    var data: List<Any>? = null
    )