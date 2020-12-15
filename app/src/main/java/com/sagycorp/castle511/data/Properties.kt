package com.sagycorp.castle511.data

import com.google.gson.annotations.SerializedName

data class Properties(

    @field:SerializedName("maxCharactersPerLine")
    val maxCharactersPerLine: Int? = null,

    @field:SerializedName("phaseDwellTime")
    val phaseDwellTime: Int? = null,

    @field:SerializedName("maxSignPhases")
    val maxSignPhases: Int? = null,

    @field:SerializedName("maxLinesPerPage")
    val maxLinesPerPage: Int? = null,

    @field:SerializedName("sizeKnown")
    val sizeKnown: Boolean? = null,

    @field:SerializedName("phaseBlankTime")
    val phaseBlankTime: Int? = null
)