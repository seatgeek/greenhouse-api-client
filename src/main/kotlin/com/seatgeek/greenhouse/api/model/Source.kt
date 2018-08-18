package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class Source(
        val id: Int,
        @SerializedName("public_name") val name: String
)
