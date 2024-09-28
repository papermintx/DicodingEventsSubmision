package com.example.dicodingevent.ui.presentation.favorite.event.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dicodingevent.domain.model.EventEntity
import com.example.dicodingevent.util.Poppins

@Composable
fun FavoriteEventItem(eventItem : EventEntity, onClick: (Int) -> Unit) {
        Column(
            Modifier
                .shadow(elevation = 4.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFF000000))
                .fillMaxWidth()
                .height(270.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onClick(eventItem.id)
                },
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(eventItem.mediaCover)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit,
            )
            Text(
                text = eventItem.name,
                style = TextStyle(
                    fontFamily = Poppins.regular
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                maxLines = 1
            )
        }




}