package com.example.vdcall.di

import com.example.vdcall.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import java.net.URI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SocketModule {


        private val TRANSPORTS = arrayOf(WebSocket.NAME)

        @Provides
        @Singleton
        fun provideSocket(): Socket {
            val socket: Socket

            val options = IO.Options.builder()
                .setTransports(TRANSPORTS)
                .setUpgrade(true)
                .setRememberUpgrade(false)
                .build()
            options.reconnection = true
            //  options.reconnectionAttempts = 1000;
            options.reconnectionDelay = 1000
            options.timeout = 10000
            socket =
                IO.socket(
                    URI.create(BuildConfig.API_BASE_URL),
                    options
                )


            return socket
        }


}