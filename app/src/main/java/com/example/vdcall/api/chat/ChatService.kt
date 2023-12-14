package com.example.vdcall.api.chat

import com.example.vdcall.api.APIClient
import com.example.vdcall.data.repository.roomdetail.RoomDetailResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService {
    @GET("chat")
    suspend fun getAllChatInRoom(
        @Path("room") room: String,
    ): List<RoomDetailResponse.GetAllChatInRoomResponse>
    @FormUrlEncoded
    @POST("room/new")
    suspend fun chatMessage(
        @Path("room") room: String,
        @Field("chatMessage") chatMessage: String,
        @Field("userName") userName: String,
    ):Any// :RoomResponse.CreateRoomResponse
    companion object: APIClient() {
        fun create(): ChatService? {
            return client?.create(ChatService::class.java)
        }
    }
}