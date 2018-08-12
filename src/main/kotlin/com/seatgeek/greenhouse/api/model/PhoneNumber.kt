package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class PhoneNumber(
    val value: String,
    val type: Type
) {
    // One of: [“home”, “work”, “mobile”, “skype”, “other”]
    enum class Type {
        @SerializedName("home")
        HOME,
        @SerializedName("work")
        WORK,
        @SerializedName("mobile")
        MOBILE,
        @SerializedName("skype")
        SKYPE,
        @SerializedName("other")
        OTHER
    }
}
