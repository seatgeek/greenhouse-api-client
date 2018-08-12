package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

/*
 {
      "id": 123,
      "opening_id": "3-1",
      "status": "open",
      "opened_at": "2015-11-20T23:14:14.736Z",
      "closed_at": "2017-11-20T23:14:14.736Z",
      "application_id": 45678,
      "close_reason": {
        "id": 678,
        "name": "Hired - Backfill"
      }
    },
 */
data class Opening(
    val id: Int,
    @SerializedName("opening_id") val openingId: String?,
    val status: Status,
    @SerializedName("opened_at") val openedAt: Date,
    @SerializedName("closed_at") val closedAt: Date,
    @SerializedName("application_id") val applicationId: Int,
    @SerializedName("close_reason") val closeReason: CloseReason
) {
    enum class Status {
        @SerializedName("open")
        OPEN,
        @SerializedName("closed")
        CLOSED;

        fun apiValue(): String {
            return when (this) {
                OPEN -> "open"
                CLOSED -> "closed"
            }
        }
    }
}
