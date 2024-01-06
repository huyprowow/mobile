package com.example.vdcall.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.vdcall.socket.SocketListeners
import com.example.vdcall.viewmodels.Room.RoomDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    fun provideRoomDetailViewModel(
//        viewModel: RoomDetailViewModel
//    ): RoomDetailViewModel {
//
//        return RoomDetailViewModel.Factory
//    }
//    @Provides
//    fun provideSocketListeners(roomDetailViewModel: RoomDetailViewModel): SocketListeners {
//        return SocketListeners(roomDetailViewModel)
//    }
    // Other dependencies

}
