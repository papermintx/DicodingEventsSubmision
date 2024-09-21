package com.example.dicodingevent.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicodingevent.ui.theme.DicodingEventTheme
import com.example.dicodingevent.util.Font
@Composable
fun ErrorScreen(modifier: Modifier = Modifier, message: String) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f) // Occupy 80% of screen width
                .padding(16.dp)
            ,
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    tint = Color.Red, // Make the icon white for contrast
                    modifier = Modifier.size(72.dp),

                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message,
                    fontFamily = Font.poppinsBold,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 18.sp // Increase font size for emphasis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    DicodingEventTheme {
        ErrorScreen(message = "Error")
    }

}