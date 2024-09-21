package com.example.dicodingevent.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dicodingevent.domain.model.Event


@Composable
fun AsyncImageCoil(modifier: Modifier = Modifier, eventItem: Event) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(eventItem.imageLogo)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .fillMaxHeight()
            .width(80.dp),
        contentScale = ContentScale.Fit
    )
}