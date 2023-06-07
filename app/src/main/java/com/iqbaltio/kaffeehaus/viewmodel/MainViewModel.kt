package com.iqbaltio.kaffeehaus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iqbaltio.kaffeehaus.data.KaffeehausRepository
import com.iqbaltio.kaffeehaus.data.api.LoginRequest
import com.iqbaltio.kaffeehaus.data.api.UserModels
import kotlinx.coroutines.launch

class MainViewModel(private val kaffeehausRepository: KaffeehausRepository) : ViewModel() {

    fun Register(name : String, password : String, email : String) =
        kaffeehausRepository.registerUser(name, email, password)

    fun Login(loginRequest: LoginRequest) =
        kaffeehausRepository.loginUser(loginRequest)

    fun getUser() : LiveData<UserModels> {
        return kaffeehausRepository.getUserLoginData()
    }

    fun storeUser(user : UserModels){
        viewModelScope.launch {
            kaffeehausRepository.storeUserData(user)
        }
    }

    fun logoutUser(){
        viewModelScope.launch {
            kaffeehausRepository.isUserLogout()
        }
    }

}