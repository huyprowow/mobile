package com.example.vdcall.socket

import android.util.Log
import com.example.vdcall.socket.Events.TRANSACTION_EVENT
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class DataSend(
    val newMessage: Any,
    var roomId: String
)
class SocketManager @Inject constructor(
    private val mSocket: Socket,
    private val listeners: SocketListeners
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
            mSocket.on(TRANSACTION_EVENT, listeners.onTransactionsListening)
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
        val onSendMessage =fun(newMessage:Any,roomId:String){
            val data=DataSend(newMessage,roomId)
            mSocket.emit(Events.SEND_MESSAGE, data);
            Log.d(
                TAG,
                "emit: $newMessage"
            )
        }

        val onNewMessage = Emitter.Listener { args ->
            Log.d(
                TAG,
                "new Message"+args
            )
        }

        companion object {
            private const val TAG = "SocketManager"
        }


}