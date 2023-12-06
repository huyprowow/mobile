package com.example.vdcall.api.room

import com.example.vdcall.api.APIClient
import com.example.vdcall.data.repository.room.RoomResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RoomService {
    @GET("room")
    suspend fun getUserRoom(
      @Query("userName") userName: String,
    ): RoomResponse.GetAllRoomResponse

    @POST("room/join")
    suspend fun joinRoom(
        @Query("roomName") roomName: String,
        @Query("roomPassword") roomPassword: String,
        @Query("userName") userName: String,
    ) //:RoomResponse.JoinRoomResponse

    @POST("room/new")
    suspend fun createRoom(
        @Query("roomName") roomName: String,
        @Query("roomPassword") roomPassword: String,
        @Query("roomDescription") roomDescription: String,
        @Query("userName") userName: String,
    )// :RoomResponse.CreateRoomResponse
    companion object:APIClient() {
        fun create(): RoomService? {
              return client?.create(RoomService::class.java)
        }
    }
}