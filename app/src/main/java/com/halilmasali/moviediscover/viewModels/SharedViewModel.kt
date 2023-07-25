package com.halilmasali.moviediscover.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel<T>: ViewModel() {
    private var _detailDataList : MutableLiveData<MutableList<T?>> = MutableLiveData()
    val detailDataList: LiveData<MutableList<T?>> = _detailDataList

    init {
        _detailDataList.value = mutableListOf()
    }

    fun addDetailData(data: T) {
        _detailDataList.value?.add(data)
        _detailDataList.value = _detailDataList.value // This line is necessary to update the value of the LiveData
    }

    fun removeLastDetailData() {
        _detailDataList.value?.removeLastOrNull()
        _detailDataList.value = _detailDataList.value // This line is necessary to update the value of the LiveData
    }
}