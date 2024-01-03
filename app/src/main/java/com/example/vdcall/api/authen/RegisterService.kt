package com.example.vdcall.api.authen

import com.example.vdcall.api.APIClient
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {
    @FormUrlEncoded
    @POST("api/user/new")
    suspend fun register(
        @Field("userName") userName: String,
        @Field("password") password:String,
    ):Any?


    companion object: APIClient() {
        fun create(): RegisterService? {
            return client?.create(RegisterService::class.java)
        }
    }
}