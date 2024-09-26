package com.example.dicodingevent.presentation.detailscreen

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.dicodingevent.presentation.components.ErrorScreen
import com.example.dicodingevent.presentation.components.LoadingScreen
import com.example.dicodingevent.util.ResultState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailEventScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    id: Int,
    onBack: () -> Unit,

    ) {
    // on below line we are creating
    // a variable for a context
    val ctx = LocalContext.current

    LaunchedEffect(id) {
        viewModel.getEvenLocaltById(id)
    }
    val uiState by viewModel.eventDetailLocalDicoding.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Event Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },

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
                    }

                    item {
                        Text(
                            text = event.name,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF000000)
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    item {
                        // Penyelenggara Acara
                        Text(
                            text = "Penyelenggara: ${event.ownerName}",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color(0xFF858585)
                            )
                        )
                    }

                    item {
                        // Waktu Acara
                        Text(
                            text = "Waktu: ${event.beginTime}",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color(0xFF858585)
                            )
                        )
                    }

                    item {
                        // Sisa Kuota
                        Text(
                            text = "Sisa Kuota: ${event.quota - event.registrants}",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color(0xFF858585)
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
                                color = Color(0xFF000000)
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
                                color = Color(0xFF858585),
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
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Buka di Browser")
                        }
                    }
                }
            }
            is ResultState.Error -> {
                ErrorScreen(message = "Error: ${state.exception.message}")
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
                            color = Color(0xFF000000),
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
