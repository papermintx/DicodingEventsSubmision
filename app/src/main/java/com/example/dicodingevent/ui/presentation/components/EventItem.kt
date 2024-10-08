package com.example.dicodingevent.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dicodingevent.R
import com.example.dicodingevent.domain.model.Event
import com.example.dicodingevent.domain.model.EventEntity
import com.example.dicodingevent.util.Poppins

@Composable
fun EventItem(
    modifier: Modifier = Modifier,
    eventItem: Event,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onClick(eventItem.id)
            }
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color(0xFFFEFAFA), shape = RoundedCornerShape(size = 16.dp))



    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(horizontal = 7.dp, vertical = 12.dp)

        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(eventItem.imageLogo)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit,
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Text(
                    text = eventItem.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = Poppins.bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = eventItem.summary,
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = Poppins.regular
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
