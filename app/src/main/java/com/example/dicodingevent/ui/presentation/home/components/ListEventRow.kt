package com.example.dicodingevent.ui.presentation.home.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.dicodingevent.R
import com.example.dicodingevent.domain.model.Event
import com.example.dicodingevent.util.Poppins


@Composable
fun ListEventRow(
    listEvent: List<Event>,
    onClick: (Int) -> Unit
) {
    LazyRow {
        items(listEvent) { event ->
            EventItemRow(eventItem = event, onClick = onClick)
        }
    }
}

@Composable
fun EventItemRow(
    eventItem: Event,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .width(330.dp)
            .clickable {
                onClick(eventItem.id)
            },
    ) {
        Column{
            AsyncImage(
                model = eventItem.mediaCover,
                contentDescription = null,
                placeholder = painterResource(R.drawable.placeholder_image),
                error = painterResource(R.drawable.placeholder_image),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = eventItem.endTime,
                    fontFamily = Poppins.bold,
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                )

            }
        }

    }

}

