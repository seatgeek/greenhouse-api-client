package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class Website(
    val value: String, // This isn't necessarily a proper URL, because this is user input that isn't validated
    val type: Type
) {
    // One of: [“personal”, “company”, “portfolio”, “blog”, “other”]
    enum class Type {
        @SerializedName("personal")
        PERSONAL,
        @SerializedName("company")
        COMPANY,
        @SerializedName("portfolio")
        PORTFOLIO,
        @SerializedName("blog")
        BLOG,
        @SerializedName("other")
        OTHER
    }
}
