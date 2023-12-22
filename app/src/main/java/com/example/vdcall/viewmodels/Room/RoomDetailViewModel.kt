package com.example.vdcall.viewmodels.Room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vdcall.data.repository.roomdetail.RoomDetailRepository
import com.example.vdcall.data.repository.roomdetail.RoomDetailResponse
import com.example.vdcall.dataStore
import com.example.vdcall.socket.SocketManager
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
    private val savedStateHandle: SavedStateHandle,
//    private val socketManager: SocketManager

): ViewModel()
{
    @Inject
    lateinit var socketManager: SocketManager
    var _userName = MutableLiveData("")
    var _message = MutableLiveData("")
    val _chats = MutableLiveData( emptyList<RoomDetailResponse.GetAllChatInRoomResponse>())

    var message: LiveData<String> = _message
    var chats: LiveData<List<RoomDetailResponse.GetAllChatInRoomResponse>> =_chats
    var userName: LiveData<String> = _userName
     val _roomId = savedStateHandle.get<String>("roomId")

    init {
        viewModelScope.launch {
            context.dataStore.data.map {
                it[EXAMPLE_COUNTER] ?: ""
            }.collect { value ->
                Log.d("Debug", "roomId: $_roomId")

                _userName.value = value
                Log.d("Debug", "userName: $value")

                if (_roomId != null) {
                    getAllChatInRoom(_roomId)
                }
            }
        }

    }
    fun getAllChatInRoom(roomId: String)
    {
        viewModelScope.launch {
            try {
                _chats.value=roomDetailRepository.getAllChatInRoom(roomId)
                Log.d("Debug", "userName: ${_chats.value}")
                Log.d("Debug", "roomId: $roomId")
            }catch (error: Exception){
                Log.d("Debug", "$error")

            }
        }
    }

    suspend fun chatMessage(roomId: String,chatMessage: String, userName: String){
       val dataRes= roomDetailRepository.chatMessage(roomId,chatMessage, userName)
        if (dataRes != null&& _roomId!=null) {
            val newChat =object {
                val chatMessage= dataRes.newChat.chatMessage
                val  dateCreated= dataRes.newChat.dateCreated
                val user=object{
                    val _id= dataRes.newChat.user
                    val  userName= userName
                }
                val __v=dataRes.newChat.__v
                val _id=dataRes.newChat._id
            };
            socketManager.onSendMessage(newChat,_roomId)
            Log.d("Debug", "${dataRes.newChat}")
        }
        _message.value =""
    }
    fun onChangeMessage(message:String){
        _message.value=message;
    }


}