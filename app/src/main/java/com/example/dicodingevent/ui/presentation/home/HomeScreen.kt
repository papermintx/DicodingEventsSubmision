package com.example.dicodingevent.ui.presentation.home

import com.example.dicodingevent.util.Poppins


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicodingevent.ui.presentation.components.ErrorScreen
import com.example.dicodingevent.ui.presentation.components.EventList
import com.example.dicodingevent.ui.presentation.components.IdleState
import com.example.dicodingevent.ui.presentation.components.LoadingScreen
import com.example.dicodingevent.ui.presentation.home.components.ListEventRow
import com.example.dicodingevent.util.ResultState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
    paddingValues: PaddingValues,
    onSettingsClick : () -> Unit
) {
    LaunchedEffect(viewModel) {
        viewModel.getEventForHome()
    }

    val eventUpcoming by viewModel.eventUpcoming.collectAsState()
    val eventFinished by viewModel.eventFinished.collectAsState()

    val stateFinished = eventFinished
    val stateUpcoming = eventUpcoming


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when ( stateUpcoming ) {
            is ResultState.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Spacer(
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Upcoming Event",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = Poppins.bold,
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Start,
                            ),
                            modifier = Modifier.padding(8.dp)
                        )

                        IconButton(
                            onClick = onSettingsClick,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color.Blue
                            )
                        }
                    }
                    // 5  Event Upcoming
                    ListEventRow(listEvent = stateUpcoming.data, onClick = onItemClick)
                    // 5  Event Finished
                    Text(
                        text = "Finished Event",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = Poppins.bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Start,
                            ),
                        modifier = Modifier.padding(8.dp)
                    )
                    EventList(
                        events = if(stateFinished is ResultState.Success) stateFinished.data else emptyList(),
                        onEventClick = onItemClick,
                    )
                    Spacer(
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
            is ResultState.Error -> {
                ErrorScreen(message = "Error: ${stateUpcoming.exception}")
            }
            is ResultState.Loading -> {
                LoadingScreen()
            }
            is ResultState.Idle ->{
               IdleState()
            }
        }
    }
}