package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.*

const val exampleJson = """{
    "id": 53883394,
    "first_name": "John",
    "last_name": "Locke",
    "company": "The Tustin Box Company",
    "title": "Man of Mystery",
    "created_at": "2017-08-15T03:31:46.591Z",
    "updated_at": "2017-09-28T12:29:30.497Z",
    "last_activity": "2017-09-28T12:29:30.481Z",
    "is_private": false,
    "photo_url": "https://prod-heroku.s3.amazonaws.com/people/photos/053/883/394/original/corgi.jpg?AWSAccessKeyId=AKIAIK36UTOKQ5F2YNMQ&Expires=1509193807&Signature=cg%2BhyNTvvNgTTzWtsMJJZvPRYH4%3D",
    "attachments": [
        {
            "filename": "John_Locke_Offer_Packet_09_27_2017.pdf",
            "url": "https://prod-heroku.s3.amazonaws.com/person_attachments/data/077/683/131/original/John_Locke_Offer_Packet_09_27_2017.pdf?AWSAccessKeyId=AKIAIK36UTOKQ5F2YNMQ&Expires=1509193807&Signature=R5TbJPzD7TO5NgX8K8Y0yogPstY%3D",
            "type": "offer_packet"
        }
    ],
    "application_ids": [
        69103370,
        65153308
    ],
    "phone_numbers": [
        {
            "value": "555-555-5555",
            "type": "mobile"
        }
    ],
    "addresses": [
        {
            "value": "123 City Street\nNew York, Ny 10001",
            "type": "home"
        }
    ],
    "email_addresses": [
        {
            "value": "test@work.com",
            "type": "work"
        }
    ],
    "website_addresses": [
        {
            "value": "mysite.com",
            "type": "personal"
        }
    ],
   "social_media_addresses": [],
    "recruiter": {
        "id": 92120,
        "first_name": "Greenhouse",
        "last_name": "Admin",
        "name": "Greenhouse Admin",
        "employee_id": null
    },
    "coordinator": null,
    "tags": [
        "Python",
        "Ruby"
    ],
    "applications": [
        {
            "id": 69103370,
            "candidate_id": 53883394,
            "prospect": true,
            "applied_at": "2017-09-27T12:21:37.234Z",
            "rejected_at": null,
            "last_activity_at": "2017-09-28T12:29:30.481Z",
            "location": {
                "address": "New York, New York, USA"
            },
            "source": {
                "id": 16,
                "public_name": "LinkedIn (Prospecting)"
            },
            "credited_to": {
                "id": 92120,
                "first_name": "Greenhouse",
                "last_name": "Admin",
                "name": "Greenhouse Admin",
                "employee_id": null
            },
            "rejection_reason": null,
            "rejection_details": null,
            "jobs": [
                {
                    "id": 87752,
                    "name": "Full Stack Engineer"
                }
            ],
            "status": "active",
            "current_stage": null,
            "answers": [],
            "prospect_detail": {
                "prospect_pool": {
                    "id": 224,
                    "name": "Cold Outreach: Sourced"
                },
                "prospect_stage": {
                    "id": 817,
                    "name": "Contacted"
                },
                "prospect_owner": {
                    "id": 92120,
                    "name": "Greenhouse Admin"
                }
            }
        },
        {
            "id": 65153308,
            "candidate_id": 53883394,
            "prospect": false,
            "applied_at": "2017-08-15T03:31:46.637Z",
            "rejected_at": null,
            "last_activity_at": "2017-09-28T12:29:30.481Z",
            "location": null,
            "source": {
                "id": 12,
                "public_name": "Meetups"
            },
            "credited_to": {
                "id": 566819,
                "first_name": "Bob",
                "last_name": "Smith",
                "name": "Bob Smith",
                "employee_id": null
            },
            "rejection_reason": null,
            "rejection_details": null,
            "jobs": [
                {
                    "id": 299100,
                    "name": "Data Scientist - BK"
                }
            ],
            "status": "active",
            "current_stage": {
                "id": 2966800,
                "name": "Face to Face"
            },
            "answers": [],
            "prospect_detail": {
                "prospect_pool": null,
                "prospect_stage": null,
                "prospect_owner": null
            }
        }
    ],
    "educations": [
        {
            "id": 561227,
            "school_name": "University of Michigan - Ann Arbor",
            "degree": "Bachelor's Degree",
            "discipline": "Computer Science",
            "start_date": "2012-08-15T00:00:00.000Z",
            "end_date": "2016-05-15T00:00:00.000Z"
        }
    ],
    "employments": [
        {
            "id": 8485064,
            "company_name": "Greenhouse",
            "title": "Engineer",
            "start_date": "2012-08-15T00:00:00.000Z",
            "end_date": "2016-05-15T00:00:00.000Z"
        }
    ],
    "custom_fields": {
        "desired_salary": "1000000000",
        "work_remotely": true,
        "graduation_year": "2018"
    },
    "keyed_custom_fields": {
        "desired_salary": {
            "name": "Desired Salary",
            "type": "short_text",
            "value": "1000000000"
        },
        "work_remotely": {
            "name": "Work Remotely",
            "type": "boolean",
            "value": true
        },
        "graduation_year_1": {
            "name": "Graduation Year",
            "type": "single_select",
            "value": "2018"
        }
     }
  }"""

data class GreenhouseCandidate(
    val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val company: String,
    val title: String,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("updated_at") val updatedAt: Date,
    @SerializedName("last_activity") val lastActivity: Date,
    @SerializedName("is_private") val isPrivate: Boolean,
    @SerializedName("photo_url") val photoUrl: URL,
    val attachments: List<Attachment>,
    @SerializedName("application_ids") val applicationIds: List<Int>,
    val coordinator: User,
    val recruiter: User,
    @SerializedName("phone_numbers") val phoneNumbers: List<PhoneNumber>,
    val addresses: List<Address>,
    @SerializedName("email_addresses") val emails: List<Email>,
    @SerializedName("website_addresses") val websites: List<Website>,
    @SerializedName("social_media_addresses") val socialMedia: List<SocialMedia>,
    val tags: List<String>,
    val applications: List<Application>,
    val educations: List<Education>,
    val employments: List<Employment>,
    @SerializedName("custom_fields") val customFields: Map<String, Any>
//    @SerializedName("keyed_custom_fields") val keyedCustomFields: Map<String, CustomField>
)
