package com.example.vdcall.data.repository.room

class RoomResponse {
    data class GetAllRoomResponse(
       val results: List<Room>,
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
class Room{

}