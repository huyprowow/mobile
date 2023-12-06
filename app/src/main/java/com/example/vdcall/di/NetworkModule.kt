package com.example.vdcall.di

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
}
