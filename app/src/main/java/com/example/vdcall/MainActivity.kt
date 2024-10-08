package com.example.vdcall

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.vdcall.compose.App
import com.example.vdcall.socket.SocketManager
import com.example.vdcall.ui.VdcallTheme
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint

class MainActivity : ComponentActivity(),CoroutineScope  {
    private var job: Job = Job()
    @Inject
    lateinit var socketManager: SocketManager

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
//    private suspend fun setUserNameDatastore(){
//        dataStore.edit{
//            it[EXAMPLE_COUNTER]="huy"
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        socketManager.socketConnect()
//        launch {
//            setUserNameDatastore()
//        }
        setContent {
            VdcallTheme {
              App()
            }
        }
    }
}