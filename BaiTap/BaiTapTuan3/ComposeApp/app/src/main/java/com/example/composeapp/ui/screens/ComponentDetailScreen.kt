package com.example.composeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import com.example.composeapp.R


@Composable
fun ComponentDetailScreen(component: String?) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "$component Detail",
            style = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
            color = Color(0xFF3190EE),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        when (component) {
            "Text" -> Text(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,

                text = buildAnnotatedString {
                    // The
                    append("The ")

                    // Quick (linethrough)
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick")
                    }

                    // brown (colored brown)
                    withStyle(style = SpanStyle(color = Color(0xFFEEAE1B))) {
                        append(" brown ")
                    }

                    // fox
                    append("fox ")

                    // jumps (letter spacing)
                    withStyle(style = SpanStyle(letterSpacing = 2.sp)) {
                        append("jumps ")
                    }

                    // over (bold)
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over ")
                    }

                    // the (underline)
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the ")
                    }

                    // lazy (cursive font family)
                    withStyle(style = SpanStyle(fontFamily = FontFamily.Cursive)) {
                        append("lazy ")
                    }

                    // dog (with dot)
                    append("dog. ")
                }
            )

            "Image" -> Image(
                painter = painterResource(id = R.drawable.moon),
                contentDescription = "Moonlight",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(400.dp)
            )

            "TextField" -> {
                // State to hold the input text
                var text by remember { mutableStateOf("") }
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Text Input") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "You typed: $text",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            "PasswordField" -> {
                // State to hold the password input
                var password by remember { mutableStateOf("") }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Enter your password below: ",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Password length: ${password.length} characters",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            "Column" -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Vertical Arrangement (Column)",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Item 1",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "Item 2",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "Item 3",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            "Row" -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Horizontal Arrangement (Row)",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Item 1",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "Item 2",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "Item 3",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            else -> Text(text = "No details available", modifier = Modifier.padding(16.dp))
        }
    }
}