package com.iqbaltio.kaffeehaus.data.api


import com.google.gson.annotations.SerializedName

data class LoginResult(
	val id: String? = null,
	val name: String? = null,
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

