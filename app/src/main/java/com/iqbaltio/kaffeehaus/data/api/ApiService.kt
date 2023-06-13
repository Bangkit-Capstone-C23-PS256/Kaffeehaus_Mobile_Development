package com.iqbaltio.kaffeehaus.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService : ApiServiceML {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ) : LoginResponse

    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ) : RegisterResponse

    @POST("preferensi")
    suspend fun addPreferensi(
        @Header("x-access-token") token : String,
        @Body request : PreferensiRequest
    ) : ResponsePreferensi

    @POST("preferensiAll")
    suspend fun getPreferensiUsers(
        @Header("x-access-token") token: String,
        @Body request : RequestPreferensiAll
    ) : ListResponsePreferensi

}