package com.iqbaltio.kaffeehaus.data.api

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(

	val success: Boolean? = null,
	val loginResult: LoginResult? = null,
	val message: String? = null
)

data class LoginResult(

	val name: String? = null,
	val id: String? = null,
	val email: String? = null
)

data class LoginRequest(
	@SerializedName("email")
	@Expose
	val email: String? = null,

	@SerializedName("password")
	@Expose
	val password: String? = null
)

data class RegisterResponse(
	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class RegisterRequest(
	val name: String? = null,
	val email: String? = null,
	val password: String? = null
)

data class UserModels(
	val name: String,
	val id: String,
	val isLogin: Boolean
)