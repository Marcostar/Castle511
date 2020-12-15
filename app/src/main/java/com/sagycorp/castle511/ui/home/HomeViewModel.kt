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

    init {
        downLoadTrafficData()
    }

    private fun downLoadTrafficData() {
        uiScope.launch {
            val getTrafficDataListDeferred = TrafficAPI.retrofitService.getTrafficData()
            try {
                val listresult = getTrafficDataListDeferred.await()
                _getTrafficDataList.value = listresult

            }catch (e: Exception)
            {
                _getTrafficDataList.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}