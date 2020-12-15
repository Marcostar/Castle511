package com.sagycorp.castle511.network

import com.sagycorp.castle511.data.TrafficData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface TrafficAPIService {

    @GET("signs")
    fun getTrafficData(): Deferred<List<TrafficData>>
}