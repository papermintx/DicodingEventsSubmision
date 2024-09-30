package com.example.dicodingevent.ui.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingevent.key.data.store.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
   private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

   val isDarkModeActive =
       userPreferencesRepository.userPreferencesFlow.map { it.isDarkModeActive }

    val isDailyReminderActive =
        userPreferencesRepository.userPreferencesFlow.map { it.isDailyReminderActive }

    fun updateIsDarkModeActive(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.updateIsDarkModeActive(isDarkModeActive)
        }
    }

    fun updateIsDailyReminderActive(isDailyReminderActive: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.updateIsDailyReminderActive(isDailyReminderActive)
        }
    }
}
