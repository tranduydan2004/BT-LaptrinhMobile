package com.example.firebaseauthdemo.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AuthStatusMessage(
    message: String,
    isSuccess: Boolean,
    modifier: Modifier = Modifier
) {
    Text(
        text = message,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )
}