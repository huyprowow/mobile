package com.example.vdcall.data.repository.roomdetail

class RoomDetailResponse {
    data class GetAllChatInRoomResponse(

       val _id: String,
       val  chatMessage: String,
       val  dateCreated: String,
       val  user: User,
       val  __v:Int

    )
}
class User(
   val _id: String,
   val userName: String
)