package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class Office(
    val id: Int,
    val name: String,
    val location: Location,
    val primaryContactUserId: Int,
    @SerializedName("parent_id") val parentId: Int,
    @SerializedName("child_ids") val childIds: List<Int>,
    @SerializedName("external_id") val externalId: String,
    @SerializedName("custom_fields") val customFields: Map<String, Any>,
    @SerializedName("hiring_team") val hiringTeam: HiringTeam,
    val openings: List<Opening>
)
