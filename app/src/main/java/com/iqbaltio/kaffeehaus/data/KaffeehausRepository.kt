package com.iqbaltio.kaffeehaus.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.iqbaltio.kaffeehaus.data.api.ApiService
import com.iqbaltio.kaffeehaus.data.api.LoginRequest
import com.iqbaltio.kaffeehaus.data.api.LoginResponse
import com.iqbaltio.kaffeehaus.data.api.PreferensiRequest
import com.iqbaltio.kaffeehaus.data.api.RegisterRequest
import com.iqbaltio.kaffeehaus.data.api.RegisterResponse
import com.iqbaltio.kaffeehaus.data.api.ResponsePreferensi
import com.iqbaltio.kaffeehaus.data.api.UserModel
import com.iqbaltio.kaffeehaus.utils.Result
import kotlin.Exception

class KaffeehausRepository(private val preferences: UserPreferences, private val apiService: ApiService) {

    fun registerUser(name : String, email : String, password : String) : LiveData<Result<RegisterResponse>> =
        liveData {
            try {
                val responseData = apiService.register(
                    RegisterRequest(name , email, password)
                )
                emit(Result.Success(responseData))
            } catch (error : Exception){
                Log.d("RegisterLOG", error.message.toString())
                emit(Result.Error(error.message.toString()))
            }
        }

    fun loginUser(loginRequest: LoginRequest) : LiveData<Result<LoginResponse>> =
        liveData {
            try {
                val responseData = apiService.login(loginRequest)
                emit(Result.Success(responseData))
            } catch (error : Exception){
                Log.d("LoginLOG", error.message.toString())
                emit(Result.Error(error.message.toString()))
            }
        }

    fun getUserLoginData() : LiveData<UserModel>{
        return preferences.getUser().asLiveData()
    }

    suspend fun storeUserData(user : UserModel){
        preferences.storeUser(user)
    }

    suspend fun isUserLogout(){
        preferences.isLogout()
    }

    fun storePreferensi(
        token : String,
        name : String,
        ambience: String,
        utils : String,
        view: String,
        userId : String,
    ) : LiveData<Result<ResponsePreferensi>> = liveData{
        emit(Result.Loading)
        try {
            val responseData = apiService.addPreferensi(token, PreferensiRequest(
                name, ambience, utils, view, userId
            ))
            emit(Result.Success(responseData))
        } catch (error : Exception){
            Log.d("PreferensiLOG", error.message.toString())
            emit(Result.Error(error.message.toString()))
        }
    }

}