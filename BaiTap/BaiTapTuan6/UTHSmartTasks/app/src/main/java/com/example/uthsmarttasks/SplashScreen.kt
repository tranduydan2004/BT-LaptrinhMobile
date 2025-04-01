package com.example.uthsmarttasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo UTH
        Image(
            painter = painterResource(id = R.drawable.logo_gtvt), // Thay bằng ID hình ảnh logo thực tế
            contentDescription = "UTH Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
        )

        // Dòng chữ nhỏ bên dưới logo
//        Text(
//            text = "University of Transport Ho Chi Minh City",
//            color = Color.Red,
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Normal,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )

        // Dòng chữ "UTH SmartTasks"
        Text(
            text = "UTH SmartTasks",
            color = Color.Blue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    UTHSmartTasksTheme {
        SplashScreen()
    }
}