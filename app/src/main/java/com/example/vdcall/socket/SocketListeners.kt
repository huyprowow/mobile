package com.example.vdcall.socket

import android.app.Application
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.vdcall.MainActivity
import com.example.vdcall.api.chat.ChatService
import com.example.vdcall.data.repository.roomdetail.RoomDetailRepository
import com.example.vdcall.viewmodels.Room.RoomDetailViewModel
import com.example.vdcall.viewmodels.Room.RoomListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


class SocketListeners @Inject constructor(
) {
//    private val roomDetailViewModel: RoomDetailViewModel
//
//    init {
//        // Create a ViewModelProvider and manually manage the ViewModel lifecycle
//        val viewModelProvider = ViewModelProvider(ViewModelStore(), ViewModelProvider.NewInstanceFactory())
//
//        // Use the provider to get or create the ViewModel
//        roomDetailViewModel = viewModelProvider.get(RoomDetailViewModel::class.java)
//    }


    var onTransactionsListening =
            Emitter.Listener { args: Array<Any> ->
                try {
                    val messageJson = JSONObject(args[0].toString())
                    Log.d(
                        TAG,
                        "SocketHelper setListening: json----   $messageJson"
                    )
                } catch (e: JSONException) {
                    Log.d(
                        TAG,
                        "SocketHelper  call: error " + e.message
                    )
                    e.printStackTrace()
                }
            }

        companion object {
            private const val TAG = "SocketListeners"
        }
}

