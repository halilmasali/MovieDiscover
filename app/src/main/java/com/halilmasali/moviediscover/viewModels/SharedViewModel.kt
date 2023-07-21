package com.halilmasali.moviediscover.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel<T>: ViewModel() {
    private var _detailData : MutableLiveData<T?> = MutableLiveData()
    val detailData: MutableLiveData<T?> = _detailData

    fun saveDetailData(data:T){
        _detailData.value = data
    }
}