package com.seatgeek.greenhouse.api

import com.google.gson.GsonBuilder
import com.seatgeek.greenhouse.api.model.GreenhouseCandidate
import com.seatgeek.greenhouse.api.model.Opening
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit

class GreenhouseClient(
    hostname: String,
    token: String,
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
                        .addHeader("Authorization", Credentials.basic(token, null))
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
    }

    class GreenhouseMethods(val service: GreenhouseService) {
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
        ): Observable<GreenhouseCandidate> {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

            return service.candidates(
                perPage,
                page,
                createdBefore?.let { dateFormat.format(createdBefore) },
                createdAfter?.let { dateFormat.format(createdAfter) },
                updatedBefore?.let { dateFormat.format(updatedBefore) },
                updatedAfter?.let { dateFormat.format(updatedAfter) },
                jobId,
                email,
                candidateIds
            ).flatMapObservable { Observable.fromIterable(it) }

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
        ): Observable<GreenhouseJob> {
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
            ).flatMapObservable { Observable.fromIterable(it) }
        }
    }
}