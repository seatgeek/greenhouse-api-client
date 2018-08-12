package com.seatgeek.greenhouse.api.model

import com.google.gson.annotations.SerializedName
import java.net.URL

/*
    "attachments": [
        {
            "filename": "John_Locke_Offer_Packet_09_27_2017.pdf",
            "url": "https://prod-heroku.s3.amazonaws.com/person_attachments/data/077/683/131/original/John_Locke_Offer_Packet_09_27_2017.pdf?AWSAccessKeyId=AKIAIK36UTOKQ5F2YNMQ&Expires=1509193807&Signature=R5TbJPzD7TO5NgX8K8Y0yogPstY%3D",
            "type": "offer_packet"
        }
    ],

 */
data class Attachment(
    val filename: String,
    val url: URL,
    val type: AttachmentType
) {
    enum class AttachmentType {
        @SerializedName("ofer_packet")
        OFFER_PACKET,
        @SerializedName("admin_only")
        ADMIN_ONLY,
        @SerializedName("public")
        PUBLIC,
        @SerializedName("cover_letter")
        COVER_LETTER,
        @SerializedName("resume")
        RESUME,
        @SerializedName("take_home_test")
        TAKE_HOME_TEST,
    }
}