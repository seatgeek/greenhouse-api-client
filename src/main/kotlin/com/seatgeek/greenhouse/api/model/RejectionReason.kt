package com.seatgeek.greenhouse.api.model

data class RejectionReason(
        val id: Int,
        val name: String,
        val type: Type
) {
    data class Type(val id: Int,
                    val name: String)
}
