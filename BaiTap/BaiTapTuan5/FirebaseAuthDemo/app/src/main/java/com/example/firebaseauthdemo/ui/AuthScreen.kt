package com.example.firebaseauthdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.firebaseauthdemo.AuthManager
import com.example.firebaseauthdemo.ui.components.AuthStatusMessage
import com.example.firebaseauthdemo.ui.components.LoginButton

@Composable
fun AuthScreen(
    authManager: AuthManager,
    onSignInClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nút "Login by Gmail"
        LoginButton(
            onClick = onSignInClick,
            modifier = Modifier
                .padding(16.dp)
                .height(50.dp)
        )

        // Hiển thị trạng thái đăng nhập
        if (authManager.authStatus.isNotEmpty()) {
            AuthStatusMessage(
                message = authManager.authStatus,
                isSuccess = authManager.isSuccess,
                modifier = Modifier
                    .background(
                        if (authManager.isSuccess) Color(0xFFADD8E6) else Color(0xFFFFCCCB)
                    )
                    .padding(16.dp)
            )
        }
    }
}