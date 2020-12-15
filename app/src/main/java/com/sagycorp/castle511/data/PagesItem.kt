package com.sagycorp.castle511.data

import com.google.gson.annotations.SerializedName

data class PagesItem(

    @field:SerializedName("justification")
    val justification: String? = null,

    @field:SerializedName("lines")
    val lines: List<String?>? = null
)