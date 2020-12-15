package com.sagycorp.castle511.data

import com.google.gson.annotations.SerializedName

data class Location(

    @field:SerializedName("perpendicularRadiansForDirectionOfTravel")
    val perpendicularRadiansForDirectionOfTravel: Double? = null,

    @field:SerializedName("signFacingDirection")
    val signFacingDirection: String? = null,

    @field:SerializedName("routeId")
    val routeId: String? = null,

    @field:SerializedName("linearReference")
    val linearReference: Double? = null,

    @field:SerializedName("locationDescription")
    val locationDescription: String? = null,

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("fips")
    val fips: Int? = null,

    @field:SerializedName("cityReference")
    val cityReference: String? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null
)