package com.sagycorp.castle511.data

import com.google.gson.annotations.SerializedName

data class TrafficData(

	@field:SerializedName("lastUpdated")
	val lastUpdated: Long? = null,

	@field:SerializedName("idForDisplay")
	val idForDisplay: String? = null,

	@field:SerializedName("display")
	val display: Display? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("agencyId")
	val agencyId: String? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("agencyName")
	val agencyName: String? = null,

	@field:SerializedName("properties")
	val properties: Properties? = null,

	@field:SerializedName("status")
	val status: String? = null
)
