package com.example.vdcall.socket

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vdcall.socket.DataClass.DataSend
import com.example.vdcall.socket.Events.TRANSACTION_EVENT
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.socket.client.Ack
import io.socket.client.Socket
import io.socket.emitter.Emitter
import javax.inject.Inject
import com.example.vdcall.socket.DataClass.DataSend.UserInfoSend
import com.example.vdcall.socket.DataClass.DataSend.UserData
import com.example.vdcall.socket.DataClass.DataSend.JoinAllRoomSend
import com.google.gson.Gson
import org.json.JSONObject



fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
class SocketManager @Inject constructor(
    val mSocket: Socket,
//    private val listeners: SocketListeners,

) {


    fun socketConnect() {
        if (!mSocket.connected()) {

            mSocket.on(Socket.EVENT_CONNECT, onConnect)
            mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect)
            mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
            mSocket.connect()


        }
    }

    fun socketDisconnect() {
        socketOff()
        mSocket.disconnect()
    }

    private fun socketOn() {
        socketOff()
//        mSocket.on(TRANSACTION_EVENT, listeners.onTransactionsListening)
    }


    private fun socketOff() {
        mSocket.off(TRANSACTION_EVENT)
    }

    /**
     * Listeners for Connect, Disconnect & Error
     */
    val onConnect = Emitter.Listener {
        Log.d(
            TAG,
            "SocketManager  isConnected " + mSocket.connected() + " |  isActive  " + mSocket.isActive
        )
        socketOn()
    }
    val onDisconnect = Emitter.Listener {
        Log.d(
            TAG,
            "SocketManager   Disconnected " + mSocket.connected() + " |  isActive  " + mSocket.isActive
        )
        socketOff()
    }
    val onConnectError = Emitter.Listener { args: Array<Any> ->
        Log.d(TAG, "SocketManager Error connecting..." + args[0].toString())
        socketOff()
    }
    val onSendMessage =fun(newMessage:DataSend.NewMessage,roomId:String){
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<DataSend.NewMessageSend> = moshi.adapter(DataSend.NewMessageSend::class.java)
        //Gson().toJson(data)
        val data = DataSend.NewMessageSend(newMessage, roomId)
        val json: String = jsonAdapter.toJson(data)

        val obj = JSONObject(json)
        mSocket.emit(Events.SEND_MESSAGE,obj);
        Log.d(
            TAG,
            "emit: $obj"
        )
    }
    val setUserInfo=fun(userName:String){
        Log.d(TAG,"setUserInfo, Hi ${userName}")
        val data= UserInfoSend(UserData(userName))
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<UserInfoSend> = moshi.adapter(UserInfoSend::class.java)
        //Gson().toJson(data)
        val json: String = jsonAdapter.toJson(data)
        Log.d(TAG,"setUserInfo, Hi ${json}")
        val obj = JSONObject(json)
        mSocket.emit(
            Events.SET_USER_INFO,
            obj,
            Ack { Log.i("Debug", "callback"+"setUserInfo, Hi ${userName}") }

        );
        Log.d(TAG,"setted")

    }

    val joinAllRoom= fun(ListRoomId:List<String>){
        try {
            val data= JoinAllRoomSend(ListRoomId)
            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<JoinAllRoomSend> = moshi.adapter(JoinAllRoomSend::class.java)
            //Gson().toJson(data)
            val json: String = jsonAdapter.toJson(data)
            val obj = JSONObject(json)
            Log.d(TAG,"setUserInfo, Hi ${obj}")
            mSocket.emit(Events.JOIN_ALL_ROOM, obj, Ack{
                Log.d("Debug","join all room success")
            });
        }catch (err:Exception){
            Log.d("Debug", "err: $err")

        }

    }


    companion object {
        private const val TAG = "SocketManager"
    }


}