package com.example.dicodingevent.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicodingevent.presentation.components.ErrorScreen
import com.example.dicodingevent.presentation.components.EventList
import com.example.dicodingevent.presentation.components.LoadingScreen
import com.example.dicodingevent.util.ResultState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListEvent(
    viewModel: MainViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
    active: Int,
    paddingValues: PaddingValues,
) {
    LaunchedEffect(active) {
        viewModel.getEventDicoding(active)
    }

    val uiState by viewModel.eventDicoding.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = uiState) {
            is ResultState.Success -> {
                EventList(events = state.data.listEvents, onEventClick = onItemClick)
            }
            is ResultState.Error -> {
                ErrorScreen(message = "Error: ${state.exception.message}")
            }
            is ResultState.Loading -> {
               LoadingScreen()
            }
            is ResultState.Idle ->{
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No data",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}