package com.example.vdcall

import android.app.Application
import androidx.work.Configuration
import com.example.vdcall.api.authen.LoginService
import com.example.vdcall.api.chat.ChatService
import com.example.vdcall.data.repository.roomdetail.RoomDetailRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application(), Configuration.Provider  {
    val roomDetailRepository: RoomDetailRepository
        get() {
            return RoomDetailRepository(ChatService.create())
        }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) android.util.Log.DEBUG else android.util.Log.ERROR)
            .build()
}