package com.example.citradisi2a.model.references

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AuthDataStore private constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val AUTH_KEY = stringPreferencesKey("token")

    fun getAuthKey(): Flow<String> {
        return dataStore.data.map {
            it[AUTH_KEY].toString()
        }
    }

    suspend fun saveAuthKey(token: String) {
        dataStore.edit {
            it[AUTH_KEY] = token
        }
    }

    suspend fun deleteAuthKey(){
        dataStore.edit{
            it.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthDataStore? = null

        fun getInstance(dataStore: DataStore<Preferences>): AuthDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = AuthDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}