package com.example.vdcall.data.repository.room

class RoomResponse {
    data class GetAllRoomResponse(
        val _id:String,
        val roomName:String,
        val roomDescription: String,
        val chats: Any,
        val users: Any,
        val roomCreatedBy: String,
        val dateCreated:String,// "2023-12-06T15:29:35.548Z",
        val __v: Int
    )
//    data class JoinRoomResponse(
//        @field:SerializedName("results") val results: List<Room>,
//        @field:SerializedName("total_pages") val totalPages: Int
//    )
//    data class CreateRoomResponse(
//        @field:SerializedName("results") val results: List<Room>,
//        @field:SerializedName("total_pages") val totalPages: Int
//    )

}