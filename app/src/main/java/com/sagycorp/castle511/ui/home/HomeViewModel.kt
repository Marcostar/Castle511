package com.sagycorp.castle511.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sagycorp.castle511.data.TrafficData
import com.sagycorp.castle511.network.TrafficAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _getTrafficDataList = MutableLiveData<List<TrafficData>>()

    val getTrafficDataList: LiveData<List<TrafficData>>
        get() = _getTrafficDataList

    private val _status = MutableLiveData<Boolean>()

    val status: LiveData<Boolean>
        get() = _status

    init {
        downLoadTrafficData()
    }

    fun downLoadTrafficData() {
        _getTrafficDataList.value = ArrayList()
        uiScope.launch {
            val getTrafficDataListDeferred = TrafficAPI.retrofitService.getTrafficData()
            try {
                val listResult = getTrafficDataListDeferred.await()
                _getTrafficDataList.value = listResult.sortedByDescending {
                    it.lastUpdated
                }
                _status.value = true

            }catch (e: Exception)
            {
                _getTrafficDataList.value = ArrayList()
                _status.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}