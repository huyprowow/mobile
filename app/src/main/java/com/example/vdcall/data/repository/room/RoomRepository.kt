package com.example.vdcall.data.repository.room

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.vdcall.api.room.RoomService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.Query
import javax.inject.Inject
@Module
@InstallIn(ViewModelComponent::class)
class RoomRepository @Inject constructor(private val service: RoomService?)  {
    suspend fun getAllRoom(userName:String): Flow<RoomResponse.GetAllRoomResponse> {
        return flow {service?.getUserRoom(userName)}
    }
    suspend fun createRoom(roomName:String,roomPassword:String,roomDescription:String, userName: String):Flow<Unit>{
        return flow {service?.createRoom(roomName,roomPassword,roomDescription, userName)}
    }
    suspend fun joinRoom(roomName:String,roomPassword:String,userName: String):Flow<Unit>{
        return flow { service?.joinRoom( roomName,roomPassword, userName) }
    }
}