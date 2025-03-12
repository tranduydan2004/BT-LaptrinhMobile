package com.example.composeapp.ui.screens

import androidx.compose.foundation.clickable // Add this import for clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ComponentsScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Title
        Text(
            text = "UI Components List",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF3190EE),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        // Display section
        Text (
            text = "Display",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ComponentItem("Text", "Displays text", navController)
        ComponentItem("Image", "Displays an image", navController)

        // Input section
        Text (
            text = "Input",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp , bottom = 8.dp)
        )
        ComponentItem("TextField", "Input field for text", navController)
        ComponentItem("PasswordField", "Input field for passwords", navController)

        // Layout section
        Text (
            text = "Layout",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        ComponentItem("Column", "Arranges elements vertically", navController)
        ComponentItem("Row", "Arranges elements horizontally", navController)
    }
}

@Composable
fun ComponentItem(name: String, description: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { // Use clickable modifier here
                navController.navigate("detail/$name")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFB3E5FC)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                fontSize = 14.sp
            )
        }
    }
}