package com.example.dicodingevent.ui.presentation.detail.event

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dicodingevent.ui.presentation.components.ErrorScreen
import com.example.dicodingevent.ui.presentation.components.LoadingScreen
import com.example.dicodingevent.util.Poppins
import com.example.dicodingevent.util.ResultState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailEventScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    id: Int,
    onBack: () -> Unit,
    ) {
    val ctx = LocalContext.current

    LaunchedEffect(id) {
        viewModel.getEventDetail(id)
    }
    val uiState by viewModel.eventDetail.collectAsState()

    val isFavorite by viewModel.eventFavorite.collectAsState()

    val redColor = Color(0xFFFF0000)
    val greyColor = Color(0xFF858585)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Event Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }

            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is ResultState.Success -> {
                val event = state.data
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding()),

                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(event.mediaCover)
                                    .crossfade(true)
                                    .build(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                            )

                            IconButton(
                                onClick = {
                                    if (isFavorite) {
                                        viewModel.deleteEventFavorite(event.id)
                                    } else {
                                        viewModel.saveEventFavorite(event)
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(16.dp)
                                    .size(48.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorite",
                                    tint = if (isFavorite) redColor else greyColor
                                )
                            }

                        }

                    }

                    item {
                        Text(
                            text = event.name,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    item {
                        Text(
                            text = "Penyelenggara: ${event.ownerName}",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        )
                    }

                    item {
                        // Waktu Acara
                        Text(
                            text = "Waktu: ${event.beginTime}",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        )
                    }

                    item {
                        // Sisa Kuota
                        Text(
                            text = "Sisa Kuota: ${event.quota - event.registrants}",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        )
                    }

                    item {
                        // Deskripsi
                        Text(
                            text = "Deskripsi",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        )
                    }

                    item {
                        val spannedDescription: Spanned = Html.fromHtml(event.description, Html.FROM_HTML_MODE_COMPACT)

                        Text(
                            text = spannedDescription.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                            ),
                            overflow = TextOverflow.Ellipsis,

                        )
                    }

                    item {
                        Button(
                            onClick = {
                                Log.e("tag","URL IS "+event.link)
                                val urlIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(event.link)
                                )

                                ctx.startActivity(urlIntent)
                            },
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Buka di Browser", style = TextStyle(
                                fontFamily = Poppins.bold,
                                color = MaterialTheme.colorScheme.surface,
                            ))
                        }
                    }
                }
            }
            is ResultState.Error -> {
                ErrorScreen(message = "Error: ${state.exception}")
            }
            ResultState.Idle -> {
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
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
            ResultState.Loading -> {
                LoadingScreen()
            }
        }
    }
}
