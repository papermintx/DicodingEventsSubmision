package com.example.dicodingevent.ui.presentation.upcomming.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicodingevent.ui.presentation.components.ErrorScreen
import com.example.dicodingevent.ui.presentation.components.EventList
import com.example.dicodingevent.ui.presentation.components.IdleState
import com.example.dicodingevent.ui.presentation.components.LoadingScreen
import com.example.dicodingevent.ui.presentation.finished.event.FinishedScreenViewModel
import com.example.dicodingevent.util.Poppins
import com.example.dicodingevent.util.ResultState

@Composable
fun UpcomingScreen(
    modifier: Modifier = Modifier,
    viewModel: UpcomingViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {
    LaunchedEffect(
        key1 = viewModel,
    ) {
        viewModel.getUpcomingEvent()
    }
    val state = viewModel.upcomingEvent.collectAsState()
    when (val result = state.value) {
        is ResultState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier.padding(bottom = 16.dp)
                )

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
                EventList(
                    events = result.data,
                    onEventClick = onClick
                )


            }

        }

        is ResultState.Error -> {
            ErrorScreen(
                message = result.exception,
            )
        }

        is ResultState.Loading -> {
            LoadingScreen()
        }

        ResultState.Idle -> {
            IdleState()
        }
    }
}

