package com.halilmasali.moviediscover.viewModels

data class ItemsViewModel (
    var priority: Int = 0,
    var title: String? = null,
    var error: Any? = null,
    var data: List<Any>? = null
    )