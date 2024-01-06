package com.example.vdcall.viewmodels.Room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vdcall.MainApplication
import com.example.vdcall.data.repository.room.RoomRepository
import com.example.vdcall.data.repository.room.RoomResponse
import com.example.vdcall.data.repository.roomdetail.RoomDetailRepository
import com.example.vdcall.data.repository.roomdetail.RoomDetailResponse
import com.example.vdcall.dataStore
import com.example.vdcall.socket.DataClass.DataReceive
import com.example.vdcall.socket.DataClass.DataSend
import com.example.vdcall.socket.Events
import com.example.vdcall.socket.SocketListeners
import com.example.vdcall.socket.SocketManager
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class RoomDetailViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val roomDetailRepository: RoomDetailRepository,
    private val savedStateHandle: SavedStateHandle,
    private val mSocket: Socket
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
    fun  processNewMessageDataFromSocket(data:JSONObject){
        val jsonString = data.toString()

        // Create Moshi instance with KotlinJsonAdapterFactory
        val moshi = Moshi.Builder()
            .build()

        // Create a JsonAdapter for your data class
        val adapter: JsonAdapter<DataReceive.MessageReceive> = moshi.adapter(
            DataReceive.MessageReceive::class.java)

        // Convert JSON string to Kotlin object
        val newMessageReceive: DataReceive.MessageReceive? = adapter.fromJson(jsonString)
        if (newMessageReceive != null) {
            _chats.postValue (_chats.value?.plus(newMessageReceive.newChat)?: listOf(newMessageReceive.newChat))
        }
    }
    val userJoined= Emitter.Listener { args ->

        val data = args[0] as JSONObject
        Log.d(
            "Debug",
            "USER JOINED: "+data
        )
    }

    val onNewMessage = Emitter.Listener { args ->
        val data = args[0] as JSONObject
        processNewMessageDataFromSocket(data)

        Log.d(
            "SocketListeners",
            "new Message"+data
        )
    }
    init {
        mSocket.on(Events.RECEIVE_MESSAGE,onNewMessage)
        mSocket.on(Events.USER_JOINED,userJoined)
        viewModelScope.launch {
            context.dataStore.data.map {
                it[EXAMPLE_COUNTER] ?: ""
            }.collect { value ->
                Log.d("Debug", "roomId: $_roomId")

                _userName.postValue(value)
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
                _chats.postValue(roomDetailRepository.getAllChatInRoom(roomId))
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
            val newChat =
                DataSend.NewMessage(
                    dataRes.newChat.chatMessage,
                    dataRes.newChat.dateCreated,
                    DataSend.UserAndIdData(dataRes.newChat.user,userName),
                    dataRes.newChat.__v,
                    dataRes.newChat._id
                )
            socketManager.onSendMessage(newChat,_roomId)
            Log.d("Debug", "${dataRes.newChat}")
        }
        _message.postValue("")
    }
    fun onChangeMessage(message:String){
        _message.postValue(message);
    }


    // Define ViewModel factory in a companion object
//    companion object {
//
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//                // Get the Application object from extras
//                val application = checkNotNull(extras[APPLICATION_KEY])
//                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()
//
//                return RoomDetailViewModel(
//                    (application ).applicationContext,
//                    (application as MainApplication).roomDetailRepository,
//                    savedStateHandle
//                ) as T
//            }
//        }
//
//
//    }
}