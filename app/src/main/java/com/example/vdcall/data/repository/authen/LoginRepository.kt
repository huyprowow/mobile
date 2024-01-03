package com.example.vdcall.data.repository.authen

import com.example.vdcall.api.authen.LoginService
import com.example.vdcall.data.repository.room.RoomResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject



    @Module
    @InstallIn(ViewModelComponent::class)
    class LoginRepository @Inject constructor(private val service: LoginService?) {
        suspend fun login(userName: String,password:String):Any? {
            return service?.login(userName,password)
        }

    }
