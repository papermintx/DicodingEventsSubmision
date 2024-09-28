package com.example.dicodingevent.ui.presentation.search.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicodingevent.ui.presentation.components.ErrorScreen
import com.example.dicodingevent.ui.presentation.components.EventList
import com.example.dicodingevent.ui.presentation.components.IdleState
import com.example.dicodingevent.ui.presentation.components.LoadingScreen
import com.example.dicodingevent.util.Poppins
import com.example.dicodingevent.util.ResultState

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val searchResults = viewModel.searchResult.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Search Event",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = Poppins.bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Start,
            ),
            modifier = Modifier.padding(8.dp)
        )
        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue
            },
            placeholder = {
                Text("Search events...")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.searchEvent(searchQuery.text)
                searchQuery = TextFieldValue("")
            },
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        ) {
            Text("Search")
        }

        when (val result = searchResults.value) {
            is ResultState.Error -> {
                ErrorScreen(
                    message = result.exception,
                )
            }
            ResultState.Idle ->{
                IdleState()
            }
            ResultState.Loading -> {
                LoadingScreen()
            }
            is ResultState.Success -> {
                if(result.data.isEmpty()){
                    ErrorScreen(
                        message = "No event found",
                    )
                } else {
                    EventList(
                        events = result.data,
                        onEventClick = onClick
                    )
                }


            }
        }
    }
}
