package com.example.vdcall.di

import com.example.vdcall.api.chat.ChatService
import com.example.vdcall.api.authen.LoginService
import com.example.vdcall.api.authen.RegisterService
import com.example.vdcall.api.room.RoomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRoomService(): RoomService? {
        return RoomService.create()
    }
    @Singleton
    @Provides
    fun provideChatService(): ChatService? {
        return ChatService.create()
    }
    @Singleton
    @Provides
    fun provideLoginService(): LoginService? {
        return LoginService.create()
    }

    @Singleton
    @Provides
    fun provideRegisterService(): RegisterService? {
        return RegisterService.create()
    }
}
