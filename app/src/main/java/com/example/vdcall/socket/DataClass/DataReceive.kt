package com.example.vdcall.socket.DataClass

import com.example.vdcall.data.repository.roomdetail.RoomDetailResponse
import com.example.vdcall.data.repository.roomdetail.User

class DataReceive {
    data class MessageReceive(
        val newChat : RoomDetailResponse.GetAllChatInRoomResponse,
        val message: String,
        val roomId:String
    )
}