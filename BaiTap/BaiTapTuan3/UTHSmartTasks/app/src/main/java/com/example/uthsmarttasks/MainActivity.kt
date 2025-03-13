package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTHSmartTasksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Hiển thị Splash Screen trong 2 giây
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        OnboardingScreen()
    }
}