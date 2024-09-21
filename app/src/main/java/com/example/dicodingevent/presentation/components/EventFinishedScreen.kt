package com.example.dicodingevent.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicodingevent.presentation.MainViewModel
import com.example.dicodingevent.presentation.components.EventList
import com.example.dicodingevent.util.ResultState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FinishedScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val uiState by viewModel.eventDicoding.collectAsState()
    Scaffold(
        topBar = {
           TopAppBar(
                title = {
                     Text("Finished Event")
                }
           )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val state = uiState) {
                is ResultState.Success -> {
                    EventList(events = state.data.listEvents, onEventClick = {})
                }
                is ResultState.Error -> {
                    Text("Error: ${state.exception.message}")
                }
                is ResultState.Loading -> {
                    CircularProgressIndicator()
                }
                else -> {
                    Text("No data")
                }
            }
        }
    }


}