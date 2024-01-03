package com.example.vdcall.api.authen



    import com.example.vdcall.api.APIClient
    import com.example.vdcall.data.repository.room.RoomResponse
    import okhttp3.OkHttpClient
    import okhttp3.logging.HttpLoggingInterceptor
    import retrofit2.Retrofit
    import retrofit2.http.Field
    import retrofit2.http.FormUrlEncoded
    import retrofit2.http.GET
    import retrofit2.http.POST



    interface LoginService {
        @FormUrlEncoded
        @POST("user/signin")
        suspend fun login(
            @Field("userName") userName: String,
            @Field("password") password:String,
        ):Any?


        companion object:APIClient() {
            fun create(): LoginService? {
                return client?.create(LoginService::class.java)
            }
        }
    }