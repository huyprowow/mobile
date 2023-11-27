package com.example.vdcall.api.room

import com.example.vdcall.api.APIClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface RoomService {
//    @GET("")
//    suspend fun searchPhotos(
//      @Query("page") page: Int,
//    ): GetAllRoomResponse

    companion object:APIClient() {
        fun create(): RoomService? {
              return client?.create(RoomService::class.java)
        }
    }
}