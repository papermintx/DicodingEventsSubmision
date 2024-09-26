package com.example.dicodingevent.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dicodingevent.domain.model.Event
import com.example.dicodingevent.domain.model.EventEntity

@Composable
fun EventList(
    events: List<EventEntity>,
    onEventClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)

    ) {
        items(events) { event ->
            EventItem(
                eventItem = event,
                onClick = onEventClick
            )
        }
    }
}