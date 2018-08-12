package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class Address(
    val value: String,
    val type: Type
) {
    enum class Type {
        @SerializedName("home")
        HOME,
        @SerializedName("work")
        WORK,
        @SerializedName("other")
        OTHER,
    }
}
