package com.example.firebaseprofile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseprofile.R
import com.example.firebaseprofile.ui.components.GoogleSignInButton

@Composable
fun LoginScreen(
    onSignInClick: () -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Phần trên: Logo, tiêu đề, và thông điệp phụ
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Logo với box bao quanh
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color(0xFFE6F0FA))
                    ) {
                        // Logo
                        Image(
                            painter = painterResource(id = R.drawable.logo_gtvt),
                            contentDescription = "UTH Logo",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    }

                    // App Name
                    Text(
                        text = "SmartTasks",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )

                    // Tagline
                    Text(
                        text = "A simple and efficient to-do app",
                        fontSize = 16.sp,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.padding(bottom = 32.dp)
                    )
                }

                // Phần giữa: Thông điệp chào mừng
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Welcome message
                    Text(
                        text = "Welcome",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Ready to explore? Log in to get started.",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )

                    // Sign in with Google button
                    GoogleSignInButton(
                        onClick = onSignInClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(50.dp)
                    )
                }

                // Phần dưới: Nút đăng nhập và footer
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Footer
                    Text(
                        text = "© UTHSmartTasks",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    )
}