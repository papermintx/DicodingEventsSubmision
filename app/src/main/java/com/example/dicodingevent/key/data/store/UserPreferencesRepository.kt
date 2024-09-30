package com.example.dicodingevent.key.data.store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

data class UserPreferences(
    val isDarkModeActive: Boolean,
    val isDailyReminderActive: Boolean
)

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)

class UserPreferencesRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val dataStore = context.dataStore

    private object PreferencesKeys{
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val DAILY_REMINDER = booleanPreferencesKey("daily_reminder")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch {
            if(it is IOException){
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            val isDarkModeActive = preferences[PreferencesKeys.DARK_MODE] ?: false
            val isDailyReminderActive = preferences[PreferencesKeys.DAILY_REMINDER] ?: false
            UserPreferences(isDarkModeActive, isDailyReminderActive)

        }

    suspend fun updateIsDarkModeActive(isDarkModeActive: Boolean){
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_MODE] = isDarkModeActive
        }
    }

    suspend fun updateIsDailyReminderActive(isDailyReminderActive: Boolean){
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DAILY_REMINDER] = isDailyReminderActive
        }
    }

}