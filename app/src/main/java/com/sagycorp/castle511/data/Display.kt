package com.sagycorp.castle511.data

import com.google.gson.annotations.SerializedName

data class Display(

    @field:SerializedName("pages")
    val pages: List<PagesItem?>? = null
)