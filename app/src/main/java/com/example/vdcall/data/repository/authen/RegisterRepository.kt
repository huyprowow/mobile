package com.example.vdcall.data.repository.authen;

import com.example.vdcall.api.authen.RegisterService;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent::class)
class RegisterRepository @Inject constructor(private val service:RegisterService?) {
    suspend fun register(userName: String,password:String):Any? {
        return service?.register(userName,password)
    }

}
