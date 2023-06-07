package com.iqbaltio.kaffeehaus.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.iqbaltio.kaffeehaus.data.api.UserModels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(private val dataStore : DataStore<Preferences>){
    fun getUser() : Flow<UserModels>{
        return dataStore.data.map { preferences ->
            UserModels(
                preferences[NAME_KEY] ?: "",
                preferences[ID_KEY] ?: "",
                preferences[STATE_KEY] ?: false
            )
        }
    }

    suspend fun storeUser(user : UserModels){
        dataStore.edit { preferences ->
            preferences[NAME_KEY] ?: user.name
            preferences[ID_KEY] ?: user.id
            preferences[STATE_KEY] ?: user.isLogin
        }
    }

    suspend fun loginUser(){
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = true
        }
    }

    suspend fun logoutUser(){
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = false
            preferences.remove(ID_KEY)
            preferences.remove(NAME_KEY)
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val ID_KEY = stringPreferencesKey("id")
        private val STATE_KEY = booleanPreferencesKey("state")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}