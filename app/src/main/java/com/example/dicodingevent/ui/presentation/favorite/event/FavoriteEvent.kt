package com.example.dicodingevent.ui.presentation.favorite.event
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.dicodingevent.ui.presentation.components.IdleState
import com.example.dicodingevent.ui.presentation.components.LoadingScreen
import com.example.dicodingevent.ui.presentation.favorite.event.components.FavoriteEventItem
import com.example.dicodingevent.util.Poppins
import com.example.dicodingevent.util.ResultState

@Composable
fun FavoriteEvent(paddingValues: PaddingValues, viewModel: FavoriteViewModel = hiltViewModel(), onEventClick: (Int) -> Unit) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getFavoriteEvent()
    }
    val uiState =  viewModel.eventFavorite.collectAsState()
    when (val state = uiState.value) {
        is ResultState.Error -> {
            ErrorScreen(message = state.exception)
        }
        ResultState.Idle -> {
            IdleState()
        }
        ResultState.Loading -> {
            LoadingScreen()
        }
        is ResultState.Success -> {
            val items = state.data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Favorite Event",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = Poppins.bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Start,
                    ),
                    modifier = Modifier.padding(8.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    items(items) { event ->
                        FavoriteEventItem(eventItem = event, onClick = onEventClick)
                    }
                    item {
                        Spacer(
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }

            }

        }
    }
}