package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class Application(
    val id: Int,
    @SerializedName("candidate_id") val candidateId: Int
)
