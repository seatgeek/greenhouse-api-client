package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

const val exampleActivityFeedJson = """{
  "notes": [
    {
      "id": 12345,
      "created_at": "2014-03-26T20:11:40Z",
      "body": "Very mysterious.",
      "user": {
        "id": 512,
        "first_name": "Sayid",
        "last_name": "Jarrah",
        "name": "Sayid Jarrah",
        "employee_id": "12345"
      },
      "private": false,
      "visiblity": "public",
      "visibility": "public"
    }
  ],
  "emails": [
    {
      "id": 234675,
      "created_at": "2014-04-01T15:55:06Z",
      "subject": "Regarding your application",
      "body": "Hey John,  just wanted to touch base!",
      "to": "john.locke@example.com",
      "from": "boone.carlyle@example.com",
      "cc": "sam.smith@example.com",
      "user": {
        "id": 214,
        "first_name": "Boone",
        "last_name": "Carlyle",
        "name": "Boone Carlyle",
        "employee_id": "67890"
      }
    }
  ],
  "activities": [
    {
      "id": 6756789,
      "created_at": "2014-04-01T15:55:29Z",
      "subject": "Candidate Rejected",
      "body": "Reason: Lacking hustle\n\nThis candidate turned out to be problematic for us...",
      "user": {
        "id": 214,
        "first_name": "Boone",
        "last_name": "Carlyle",
        "name": "Boone Carlyle",
        "employee_id": "67890"
      }
    },
    {
      "id": 6757869,
      "created_at": "2014-03-26T20:26:38Z",
      "subject": "Candidate Stage Change",
      "body": "John Locke was moved into Recruiter Phone Screen for Accounting Manager on 03/27/2014 by Boone Carlyle",
      "user": {
        "id": 214,
        "first_name": "Boone",
        "last_name": "Carlyle",
        "name": "Boone Carlyle",
        "employee_id": "67890"
      }
    }
  ]
}"""

data class ActivityFeed(
    val notes: List<Note>,
    val emails: List<Email>,
    val activities: List<Activity>
) {
    data class Note(
        val id: Int,
        @SerializedName("created_at") val createdAt: Date,
        val body: String,
        val user: User,
        val private: Boolean,
        val visibility: Visibility
    ) {
        enum class Visibility {
            @SerializedName("public")
            PUBLIC,
            @SerializedName("admin_only")
            ADMIN_ONLY,
            @SerializedName("private")
            PRIVATE
        }
    }

    data class Email(
        val id: Int,
        @SerializedName("created_at") val createdAt: Date,
        val subject: String,
        val body: String,
        val to: String,
        val from: String,
// TODO the API sometimes gives a string, and sometimes a list of String. Need a "possibly" array type
//        val cc: List<String>,
        val user: User
    )

    data class Activity(
        val id: Int,
        @SerializedName("created_at") val createdAt: Date,
        val subject: String,
        val body: String,
        val user: User
    )
}
