package com.iqbaltio.kaffeehaus.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceML {

    @GET("caffe")
    suspend fun listCaffe(
    ) : ListResponseCafe

    @POST("/search")
    suspend fun searchCafe(
        @Query("user_input") user_input : String
    ) : ListResponseSearch

}