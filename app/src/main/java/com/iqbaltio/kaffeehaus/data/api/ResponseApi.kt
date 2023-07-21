package com.iqbaltio.kaffeehaus.data.api


import com.google.gson.annotations.SerializedName

data class LoginResult(
	val id: String? = null,
	val name: String? = null,
	val email: String? = null,
	val token: String? = null
)

data class LoginResponse(
	val loginResult: LoginResult? = null,
	val success: Boolean? = null,
	val message: String? = null
)

data class LoginRequest(
	@SerializedName("email")
	val email: String? = null,

	@SerializedName("password")
	val password: String? = null
)

data class RegisterResponse(
	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class RegisterRequest(
	val name: String? = null,
	val email: String? = null,
	val password: String? = null
)

data class UserModel(
	val id : String,
	val name: String,
	val email : String,
	val token : String,
	val isLogin: Boolean
)


data class PreferensiRequest(
	val name: String? = null,
	val ambience: String? = null,
	val utils: String? = null,
	val view: String? = null,
	val userId: String? = null
)

data class ResponsePreferensi(
	val preferensiResult: PreferensiRequest? = null,
	val success: Boolean? = null,
	val message: String? = null
)


data class ListResponseCafe(

	@field:SerializedName("cafe")
	val cafe: ArrayList<CafeItem>,

	@field:SerializedName("message")
	val message: String
)


data class CafeItem(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("url_photo")
	val urlPhoto: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: String,

	@field:SerializedName("total_review")
	val totalReview: Int,

	@field:SerializedName("longitude")
	val longitude: Double,

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("place_id")
	val placeId: String
)

data class RequestSearch(
	val user_input: String? = null
)

data class RequestPreferensiAll(
	val userId : String? = null
)

data class ListResponseSearch(

	@field:SerializedName("search")
	val search: ArrayList<CafeItem>,

	@field:SerializedName("message")
	val message: String
)

data class ListResponsePreferensi(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("preferensiData")
	val preferensiData: ArrayList<PreferensiDataItem>,

	@field:SerializedName("message")
	val message: String? = null
)

data class PreferensiDataItem(

	@field:SerializedName("view")
	val view: String? = null,

	@field:SerializedName("ambience")
	val ambience: String? = null,

	@field:SerializedName("utils")
	val utils: String? = null,

)



