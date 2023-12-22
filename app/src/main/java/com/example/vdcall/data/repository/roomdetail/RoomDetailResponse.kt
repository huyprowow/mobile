package com.example.vdcall.data.repository.roomdetail

class RoomDetailResponse {
    data class GetAllChatInRoomResponse(

       val _id: String,
       val  chatMessage: String,
       val  dateCreated: String,
       val  user: User,
       val  __v:Int

    )
data class ChatMessageResponse(
val message:String,
   val newChat:NewChat
)
}

class NewChat (
    val chatMessage:String,
    val dateCreated:String,
    val user:String,
    val _id:String,
    val __v:Number
)

class User(
   val _id: String,
   val userName: String
)