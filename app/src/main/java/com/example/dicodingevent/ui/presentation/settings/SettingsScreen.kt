package com.example.dicodingevent.ui.presentation.settings


import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    boolean: Boolean,
    boolean2: Boolean,
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp), // Add padding for a better appearance
            verticalArrangement = Arrangement.spacedBy(16.dp), // Space between items
            horizontalAlignment = Alignment.Start
        ) {
            // Dark Mode Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Enable Dark Mode", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = boolean,
                    onCheckedChange = {
                        viewModel.updateIsDarkModeActive(it)
                    }
                )
            }
            Text("Switch to dark mode for a better experience in low light.",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
                )
            )

            // Notifications Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Enable Daily Reminder", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = boolean2,
                    onCheckedChange = { isChecked ->
                        viewModel.updateIsDailyReminderActive(isChecked)
                    }
                )
            }
            Text("Get daily reminders for upcoming events.\n this features is still in development.",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
                )
            )
        }
    }
}
