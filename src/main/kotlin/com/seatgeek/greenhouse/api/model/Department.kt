package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class Department(
    val id: Int,
    val name: String,
    @SerializedName("parent_id") val parentId: Int,
    @SerializedName("child_ids") val childIds: List<Int>,
    @SerializedName("external_id") val externalId: String
)
