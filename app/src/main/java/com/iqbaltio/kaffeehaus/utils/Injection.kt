package com.iqbaltio.kaffeehaus.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.iqbaltio.kaffeehaus.data.KaffeehausRepository
import com.iqbaltio.kaffeehaus.data.UserPreferences
import com.iqbaltio.kaffeehaus.data.api.ApiConfig
import com.iqbaltio.kaffeehaus.data.api.ApiConfigML

val Context.dataStore : DataStore<Preferences> by preferencesDataStore("settings")

object Injection{
    fun provideRepository(context: Context) : KaffeehausRepository {
        val preferences = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        val apiServiceML = ApiConfigML.getApiService()
        return KaffeehausRepository(preferences, apiService, apiServiceML)
    }
}