package com.example.vdcall.socket.DataClass

class DataSend {
    data class UserInfoSend(
        val user :UserData
    )
   data class UserData(
        val userName:String,
    )
   data class JoinAllRoomSend(
       val rooms:List<String>
   )
    data class NewMessageSend(
       val newChat:NewMessage,
       val roomId:String
    )
   data class NewMessage(
        val chatMessage:String,
        val  dateCreated:String,
        val user:UserAndIdData,
        val __v:Int,
        val _id:String
    )
    data class UserAndIdData(
        val _id:String,
        val userName:String,
    )

}
