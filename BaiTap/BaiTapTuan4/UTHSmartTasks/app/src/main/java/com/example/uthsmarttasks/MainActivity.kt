package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.uthsmarttasks.data.Task
import com.example.uthsmarttasks.ui.*
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTHSmartTasksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    var showSplash by remember { mutableStateOf(true) }
                    var showOnboarding by remember { mutableStateOf(false) }
                    var showTaskScreens by remember { mutableStateOf(false) }

                    LaunchedEffect(Unit) {
                        delay(2000)
                        showSplash = false
                        showOnboarding = true
                    }

                    if (showSplash) {
                        SplashScreen()
                    } else if (showOnboarding) {
                        OnboardingScreen(
                            onSkip = {
                                scope.launch {
                                    showOnboarding = false
                                    showTaskScreens = true
                                }
                            },
                            onGetStarted = {
                                scope.launch {
                                    showOnboarding = false
                                    showTaskScreens = true
                                }
                            }
                        )
                    } else if (showTaskScreens) {
                        TaskNavigation()
                    }
                }
            }
        }
    }
}

@Composable
fun TaskNavigation() {
    var currentScreen by remember { mutableStateOf("List") }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    when (currentScreen) {
        "List" -> ListScreen(
            onItemClick = { task ->
                selectedTask = task
                currentScreen = "Detail"
            },
            onEmptyList = {
                currentScreen = "ListEmpty"
            }
        )
        "ListEmpty" -> ListEmptyScreen()
        "Detail" -> DetailScreen(
            task = selectedTask,
            onBack = {
                currentScreen = "List"
                selectedTask = null
            },
            onDelete = {
                // Logic xóa task (bổ sung sau)
                currentScreen = "List"
                selectedTask = null
            }
        )
    }
}