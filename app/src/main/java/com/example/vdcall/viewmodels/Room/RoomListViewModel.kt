package com.example.vdcall.viewmodels.Room

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vdcall.data.repository.room.RoomRepository
import com.example.vdcall.dataStore
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
):ViewModel() {
    private var _userName = MutableLiveData("")
    var userName: LiveData<String> = _userName
    private var _openJoinRoomDialog = MutableLiveData(false)
    var openJoinRoomDialog: LiveData<Boolean> = _openJoinRoomDialog
    private var _openCreateRoomDialog = MutableLiveData(false)
    var openCreateRoomDialog: LiveData<Boolean> = _openCreateRoomDialog

    //   fun createRoom(roomName:String,roomPassword:String,roomDescription:String, userName: String){
//       roomRepository.createRoom(roomName,roomPassword,roomDescription, userName)
//    }
    init{
        viewModelScope.launch {
            context.dataStore.data.map {
                it[EXAMPLE_COUNTER] ?: ""
            }.collect { value ->
                _userName.value = value
            }
        }
    }
    fun toggleJoinRoomDialog(){
        _openJoinRoomDialog.value = _openJoinRoomDialog.value != true
    }
    fun toggleCreateRoomDialog(){
        _openCreateRoomDialog.value = _openCreateRoomDialog.value != true
    }


}