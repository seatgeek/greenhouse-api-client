package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

/*
{
  "id": 112,
  "name": "Juliet Burke",
  "first_name": "Juliet",
  "last_name": "Burke",
  "updated_at": "2016-11-17T16:13:48.888Z",
  "created_at": "2015-11-18T22:26:32.243Z",
  "disabled": false,
  "site_admin": true,
  "emails": [
    "juliet.burke@example.com",
    "other.woman@example.com"
  ],
  "employee_id": "221"
}
 */
data class User(
    val id: Int,
    val name: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("updated_at") val updatedAt: Date,
    @SerializedName("created_at") val createdAt: Date,
    val disabled: Boolean,
    @SerializedName("site_admin") val siteAdmin: Boolean,
    val emails: List<String>,
    @SerializedName("employee_id") val employeeId: String
)