package com.iqbaltio.kaffeehaus.data

data class ImageData(
    val imageUrl: String
)

data class CafeData(
    val imageUrl : String,
    val title : String,
    val address : String,
    val rating : Int
)