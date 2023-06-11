package com.iqbaltio.kaffeehaus.data.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceML {

    @GET("caffe")
    suspend fun listCaffe(
    ) : ListResponseCafe

}