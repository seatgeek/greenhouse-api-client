package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName

data class HiringTeam(
    @SerializedName("hiring_managers") val hiringManagers: List<User>,
    val recruiters: List<User>,
    val coordinators: List<User>,
    val sourcers: List<User>
)
