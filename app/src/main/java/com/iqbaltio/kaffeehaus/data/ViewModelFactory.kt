package com.iqbaltio.kaffeehaus.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iqbaltio.kaffeehaus.utils.Injection
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class ViewModelFactory(private val repo : KaffeehausRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class : ${modelClass.name}")
        }
    }

    companion object{
        @Volatile
        private var INSTANCE : ViewModelFactory?=null
        fun getInstance(context: Context) : ViewModelFactory {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { INSTANCE = it }
        }
    }
}