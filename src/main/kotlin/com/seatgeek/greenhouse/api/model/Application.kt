package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName
import com.seatgeek.greenhouse.api.GreenhouseJob
import java.util.*

const val exampleApplicationJson = """{
            "id":54321,
            "candidate_id":12345,
            "prospect": false,
            "applied_at": "2017-09-27T12:03:02.728Z",
            "rejected_at": "2017-09-27T12:11:40.877Z",
            "last_activity_at": "2017-09-28T12:29:30.481Z",
            "location": {
                "address": "New York, New York, USA"
            },
            "source": {
                "id": 16,
                "public_name": "LinkedIn (Prospecting)"
            },
            "credited_to": {
                "id": 165372,
                "first_name": "Joel",
                "last_name": "Job Admin",
                "name": "Joel Job Admin",
                "employee_id": null
            },
            "rejection_reason": {
                "id": 9504,
                "name": "Hired another candidate",
                "type": {
                    "id": 1,
                    "name": "We rejected them"
                }
            },
            "rejection_details": {
                "custom_fields": {
                    "custom_rejection_question_field": null
                },
                "keyed_custom_fields": {
                    "custom_rejection_question_field": {
                        "name": "Custom Rejection Question Field",
                        "type": "short_text",
                        "value": null
                    }
                }
            },
            "jobs": [
                {
                    "id": 149995,
                    "name": "DevOps Engineer"
                }
            ],
            "status": "rejected",
            "current_stage": {
                "id": 1073533,
                "name": "Take Home Test"
            },
            "answers": [
                {
                    "question": "How did you hear about this job?",
                    "answer": "A friend"
                },
                {
                    "question": "Website",
                    "answer": "https://example.com"
                },
                {
                    "question": "LinkedIn Profile",
                    "answer": "https://linkedin.com/example"
                }
            ],
            "prospect_detail": {
                "prospect_pool": null,
                "prospect_stage": null,
                "prospect_owner": null
            }
        }"""

data class Application(
        val id: Int,
        @SerializedName("candidate_id") val candidateId: Int,
        @SerializedName("prospect") val isProspect: Boolean,
        @SerializedName("applied_at") val appliedAt: Date,
        @SerializedName("rejected_at") val rejectedAt: Date,
        @SerializedName("last_activity_at") val lastActivityAt: Date,
        val location: Address,
        val source: Source,
        @SerializedName("credited_to") val creditedTo: User,
        @SerializedName("rejection_reason") val rejectionReason: RejectionReason,
        @SerializedName("rejection_details") val rejectionDetails: RejectionDetails,
        val jobs: List<Job>,
        val status: Status,
        @SerializedName("current_stage") val currentStage: Stage,
        val answers: List<Map<String, String>>
        ) {
    enum class Status {
        @SerializedName("active")
        ACTIVE,
        @SerializedName("hired")
        HIRED,
        @SerializedName("rejected")
        REJECTED
    }

    data class Stage(val id: Int,
                     val name: String)
}
