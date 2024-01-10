package com.example.vdcall.viewmodels.Room

import android.content.Context
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.vdcall.Activity.dataStore
import com.example.vdcall.data.repository.room.RoomRepository
import com.example.vdcall.data.repository.room.RoomResponse
import com.example.vdcall.socket.SocketManager
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class RoomListViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val roomRepository: RoomRepository,
    private val socketManager: SocketManager,

):ViewModel() {
//    private val _state=mutableStateOf(// dang ra la phai viet kieu nay ma luoi tao doi tuong :v
//        object {
//            var _userName = MutableLiveData("")
//            var _openJoinRoomDialog = MutableLiveData(false)
//            var _openCreateRoomDialog = MutableLiveData(false)
//        }
//    )
//     val state:RoomListState= _state

    var _userName = MutableLiveData("")
    var _openJoinRoomDialog = MutableLiveData(false)
    var _openCreateRoomDialog = MutableLiveData(false)
    val _rooms =MutableLiveData( emptyList<RoomResponse.GetAllRoomResponse>())


    var openJoinRoomDialog: LiveData<Boolean> = _openJoinRoomDialog
    var userName: LiveData<String> = _userName
    var openCreateRoomDialog: LiveData<Boolean> = _openCreateRoomDialog
    var rooms:LiveData<List<RoomResponse.GetAllRoomResponse>> =_rooms
    init {
        viewModelScope.launch {
            context.dataStore.data.map {
                it[EXAMPLE_COUNTER] ?: ""
            }.collect { value ->
                _userName.value = value

                Log.d("Debug", "userName: $value")
                getAllRoom(value)
            }
        }

    }
    suspend fun getAllRoom(userName: String)
    {
                try {
                    socketManager.setUserInfo(userName)
                    _rooms.value=roomRepository.getAllRoom(userName)

//                    .collect{
//                    = listOf(it)
                    val listRoomId = _rooms.value?.map { it._id }
                    if (listRoomId != null) {
                        socketManager.joinAllRoom(listRoomId)
                        Log.d("Debug", "List room id: ${listRoomId}")

                    }
                    Log.d("Debug", "List room: ${_rooms.value}")

//                }
                    Log.d("Debug", "userName: $userName")
                }catch (error: Exception){
                    Log.d("Debug", "$error")

                }

    }

    suspend fun createRoom(roomName:String, roomPassword:String, roomDescription:String, userName: String){
       roomRepository.createRoom(roomName,roomPassword,roomDescription, userName)
    }
    suspend fun joinRoom(roomName:String, roomPassword:String, userName: String){
        roomRepository.joinRoom(roomName, roomPassword,userName)
    }
    fun toggleJoinRoomDialog(){
        _openJoinRoomDialog.value = _openJoinRoomDialog.value != true
    }
    fun toggleCreateRoomDialog(){
        _openCreateRoomDialog.value = _openCreateRoomDialog.value != true
    }


}