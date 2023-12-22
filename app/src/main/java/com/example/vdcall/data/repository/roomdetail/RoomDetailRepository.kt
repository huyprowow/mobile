package com.example.vdcall.data.repository.roomdetail

import com.example.vdcall.api.chat.ChatService
import javax.inject.Inject

class RoomDetailRepository @Inject constructor(private val service: ChatService?)  {
    suspend fun getAllChatInRoom(roomId:String): List<RoomDetailResponse.GetAllChatInRoomResponse>? {
        return service?.getAllChatInRoom(roomId)
    }
    suspend fun chatMessage(room:String,chatMessage:String,userName: String):RoomDetailResponse.ChatMessageResponse? {
        return service?.chatMessage(room,chatMessage, userName)
    }


}