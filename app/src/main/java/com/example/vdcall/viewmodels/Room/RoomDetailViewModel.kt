package com.example.vdcall.viewmodels.Room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vdcall.data.repository.room.RoomRepository
import com.example.vdcall.data.repository.room.RoomResponse
import com.example.vdcall.data.repository.roomdetail.RoomDetailRepository
import com.example.vdcall.dataStore
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDetailViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val roomDetailRepository: RoomDetailRepository,
): ViewModel()
{
//    var _userName = MutableLiveData("")
//    var _openJoinRoomDialog = MutableLiveData(false)
//    var _openCreateRoomDialog = MutableLiveData(false)
//    val _rooms = MutableLiveData( emptyList<RoomResponse.GetAllRoomResponse>())
//
//
//    var openJoinRoomDialog: LiveData<Boolean> = _openJoinRoomDialog
//    var userName: LiveData<String> = _userName
//    var openCreateRoomDialog: LiveData<Boolean> = _openCreateRoomDialog
//    var rooms: LiveData<List<RoomResponse.GetAllRoomResponse>> =_rooms
//    init {
//        viewModelScope.launch {
//            context.dataStore.data.map {
//                it[EXAMPLE_COUNTER] ?: ""
//            }.collect { value ->
//                _userName.value = value
//                Log.d("Debug", "userName: $value")
//                getAllRoom(value)
//            }
//        }
//
//    }
//    fun getAllRoom(userName: String)
//    {
//        viewModelScope.launch {
//            try {
//                _rooms.value=roomDetailRepository.getAllRoom(userName)
////                    .collect{
////                    = listOf(it)
//                Log.d("Debug", "userName: ${_rooms.value}")
//
////                }
//                Log.d("Debug", "userName: $userName")
//            }catch (error: Exception){
//                Log.d("Debug", "$error")
//
//            }
//        }
//    }
//
//    suspend fun createRoom(roomName:String, roomPassword:String, roomDescription:String, userName: String){
//        roomRepository.createRoom(roomName,roomPassword,roomDescription, userName)
//    }
//    suspend fun joinRoom(roomName:String, roomPassword:String, userName: String){
//        roomRepository.joinRoom(roomName, roomPassword,userName)
//    }
//    fun toggleJoinRoomDialog(){
//        _openJoinRoomDialog.value = _openJoinRoomDialog.value != true
//    }
//    fun toggleCreateRoomDialog(){
//        _openCreateRoomDialog.value = _openCreateRoomDialog.value != true
//    }


}