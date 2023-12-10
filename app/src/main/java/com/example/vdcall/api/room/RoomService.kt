package com.example.vdcall.api.room

import com.example.vdcall.api.APIClient
import com.example.vdcall.data.repository.room.RoomResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//android:networkSecurityConfig="@xml/network_security_config"

interface RoomService {
    @FormUrlEncoded
    @POST("room")
    suspend fun getUserRoom(
        @Field("userName") userName: String,
    ): List<RoomResponse.GetAllRoomResponse>
    @FormUrlEncoded
    @POST("room/join")
    suspend fun joinRoom(
        @Field("roomName") roomName: String,
        @Field("roomPassword") roomPassword: String,
        @Field("userName") userName: String,
    ):Any //:RoomResponse.JoinRoomResponse
    @FormUrlEncoded
    @POST("room/new")
    suspend fun createRoom(
        @Field("roomName") roomName: String,
        @Field("roomPassword") roomPassword: String,
        @Field("roomDescription") roomDescription: String,
        @Field("userName") userName: String,
    ):Any// :RoomResponse.CreateRoomResponse
    companion object:APIClient() {
        fun create(): RoomService? {
              return client?.create(RoomService::class.java)
        }
    }
}