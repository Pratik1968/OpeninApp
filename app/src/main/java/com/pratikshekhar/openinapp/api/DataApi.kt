package com.pratikshekhar.openinapp.api

import com.pratikshekhar.openinapp.model.Api_Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DataApi {
    @GET("api/v1/dashboardNew")
    suspend fun getData(@Header("Authorization") token:String ):Response<Api_Response>
    @POST("api/v1/dashboardNew")
    suspend fun postData(@Header("Authorization") token:String):Response<Any>
}