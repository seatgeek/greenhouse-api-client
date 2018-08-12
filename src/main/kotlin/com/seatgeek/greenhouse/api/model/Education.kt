package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Education(
    val id: Int,
    @SerializedName("school_name") val schoolName: String,
    val degree: String,
    val discipline: String,
    @SerializedName("start_date") val startedAt: Date,
    @SerializedName("end_date") val endedAt: Date
)
