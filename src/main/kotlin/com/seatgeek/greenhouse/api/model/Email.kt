package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class Email(
    val value: String,
    val type: Type
) {
    // One of: [“personal”, “work”, “other”]
    enum class Type {
        @SerializedName("personal")
        PERSONAL,
        @SerializedName("work")
        WORK,
        @SerializedName("other")
        OTHER
    }
}
