package com.iqbaltio.kaffeehaus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.iqbaltio.kaffeehaus.data.KaffeehausRepository
import com.iqbaltio.kaffeehaus.data.api.CafeItem
import com.iqbaltio.kaffeehaus.data.api.ListResponseCafe
import com.iqbaltio.kaffeehaus.data.api.LoginRequest
import com.iqbaltio.kaffeehaus.data.api.UserModel
import com.iqbaltio.kaffeehaus.utils.Result
import kotlinx.coroutines.launch

class MainViewModel(private val kaffeehausRepository: KaffeehausRepository) : ViewModel() {

    fun Register(name : String, email : String, password : String) =
        kaffeehausRepository.registerUser(name, email, password)

    fun Login(loginRequest: LoginRequest) =
        kaffeehausRepository.loginUser(loginRequest)

    fun getUser() : LiveData<UserModel> {
        return kaffeehausRepository.getUserLoginData()
    }

    fun storeUser(user : UserModel){
        viewModelScope.launch {
            kaffeehausRepository.storeUserData(user)
        }
    }

    fun logoutUser(){
        viewModelScope.launch {
            kaffeehausRepository.isUserLogout()
        }
    }

    fun storePreferensi(token : String, name : String, ambience: String, utils : String, view: String, userId : String ) =
        kaffeehausRepository.storePreferensi(token, name, ambience, utils, view, userId)

    fun getCaffeList() : LiveData<Result<ListResponseCafe>> =
        kaffeehausRepository.getCafeItemList()



}