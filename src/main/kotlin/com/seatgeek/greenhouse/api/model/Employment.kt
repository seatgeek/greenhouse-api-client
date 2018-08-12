package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Employment(
    val id: Int,
    @SerializedName("company_name") val companyName: String,
    val title: String,
    @SerializedName("start_date") val startedAt: Date,
    @SerializedName("end_date") val endedAt: Date
)
