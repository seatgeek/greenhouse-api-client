package com.seatgeek.greenhouse.api

import com.google.gson.GsonBuilder
import com.seatgeek.greenhouse.api.model.ActivityFeed
import com.seatgeek.greenhouse.api.model.Application
import com.seatgeek.greenhouse.api.model.GreenhouseCandidate
import com.seatgeek.greenhouse.api.model.Opening
import io.reactivex.Single
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit

class GreenhouseClient(
        token: String,
        hostname: String = "harvest.greenhouse.io",
        timeout: Duration? = null
) {
    val methods: GreenhouseMethods

    init {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            println(it)
        })

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okhttpBuilder = OkHttpClient.Builder()
                .addInterceptor {
                    it.proceed(
                            it.request().newBuilder()
                                    .addHeader("Authorization", Credentials.basic(token, ""))
                                    .build()
                    )
                }
                .addInterceptor(loggingInterceptor)

        if (timeout != null) {
            okhttpBuilder.readTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS)
        }

        val okHttpClient = okhttpBuilder
                .build()

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://${hostname}/v1/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        methods = GreenhouseMethods(retrofit.create(GreenhouseService::class.java))
    }

    interface GreenhouseService {
        @GET("candidates")
        fun candidates(
                @Query("per_page") perPage: Int?,
                @Query("page") page: Int?,
                @Query("created_before") createdBefore: String?,
                @Query("created_after") createdAfter: String?,
                @Query("updated_before") updatedBefore: String?,
                @Query("updated_after") updatedAfter: String?,
                @Query("job_id") jobId: Int?,
                @Query("email") email: String?,
                @Query("candidate_ids") candidateIds: List<Int>?
        ): Single<List<GreenhouseCandidate>>

        @GET("candidates/{id}")
        fun candidate(@Path("id") candidateId: Int): Single<GreenhouseCandidate>

        /*
        per_page	Return up to this number of objects per response. Must be an integer between 1 and 500. Defaults to 100.
        page	A cursor for use in pagination. Returns the n-th chunk of per_page objects.
        created_before	Return only jobs that were created before this timestamp. Timestamp must be in in ISO-8601 format.
        created_after	Return only jobs that were created at or after this timestamp. Timestamp must be in in ISO-8601 format.
        updated_before	Return only jobs that were updated before this timestamp. Timestamp must be in in ISO-8601 format.
        updated_after	Return only jobs that were updated at or after this timestamp. Timestamp must be in in ISO-8601 format.
        requisition_id	If included, will return only the jobs that match the given requisition_id
        opening_id	If included, will return only the jobs that contain at least one opening with the given opening_id.
        status	One of ‘open’, 'closed’, or 'draft’. If included, will only return jobs with that status.
         */
        @GET("jobs")
        fun jobs(
                @Query("per_page") perPage: Int?,
                @Query("page") page: Int?,
                @Query("created_before") createdBefore: String?,
                @Query("created_after") createdAfter: String?,
                @Query("updated_before") updatedBefore: String?,
                @Query("updated_after") updatedAfter: String?,
                @Query("requisition_id") requisitionId: String?,
                @Query("opening_id") openingId: Int?,
                @Query("status") status: String?
        ): Single<List<GreenhouseJob>>

        @GET("applications")
        fun applications(
                @Query("per_page") perPage: Int?,
                @Query("page") page: Int?,
                @Query("created_before") createdBefore: String?,
                @Query("created_after") createdAfter: String?,
                @Query("last_activity_after") lastActivityAfter: String?,
                @Query("job_id") jobId: Int?,
                @Query("status") status: Application.Status?
        ): Single<List<Application>>

        @GET("candidates/{id}/activity_feed")
        fun activityFeed(
                @Path("id") id: Int
        ): Single<ActivityFeed>
    }

    class GreenhouseMethods(val service: GreenhouseService) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        fun candidates(
                perPage: Int? = null,
                page: Int? = null,
                createdBefore: Date? = null,
                createdAfter: Date? = null,
                updatedBefore: Date? = null,
                updatedAfter: Date? = null,
                jobId: Int? = null,
                email: String? = null,
                candidateIds: List<Int>? = null
        ): Single<List<GreenhouseCandidate>> {
            return service.candidates(
                    perPage,
                    page,
                    createdBefore?.let { dateFormat.format(it) },
                    createdAfter?.let { dateFormat.format(it) },
                    updatedBefore?.let { dateFormat.format(it) },
                    updatedAfter?.let { dateFormat.format(it) },
                    jobId,
                    email,
                    candidateIds
            )
        }

        fun candidate(
                candidateId: Int
        ): Single<GreenhouseCandidate> {
            return service.candidate(
                    candidateId
            )
        }

        fun applications(
                perPage: Int? = null,
                page: Int? = null,
                createdBefore: String? = null,
                createdAfter: String? = null,
                lastActivityAfter: String? = null,
                jobId: Int? = null,
                status: Application.Status? = null
        ): Single<List<Application>> {
            return service.applications(
                    perPage,
                    page,
                    createdBefore?.let { dateFormat.format(it) },
                    createdAfter?.let { dateFormat.format(it) },
                    lastActivityAfter?.let { dateFormat.format(it) },
                    jobId,
                    status
            )
        }

        fun jobs(
                perPage: Int? = null,
                page: Int? = null,
                createdBefore: String? = null,
                createdAfter: String? = null,
                updatedBefore: String? = null,
                updatedAfter: String? = null,
                requisitionId: String? = null,
                openingId: Int? = null,
                status: Opening.Status? = null
        ): Single<List<GreenhouseJob>> {
            return service.jobs(
                    perPage,
                    page,
                    createdBefore,
                    createdAfter,
                    updatedBefore,
                    updatedAfter,
                    requisitionId,
                    openingId,
                    status?.apiValue()
            )
        }

        fun activityFeed(candidateId: Int): Single<ActivityFeed> {
            return service.activityFeed(candidateId)
        }
    }
}