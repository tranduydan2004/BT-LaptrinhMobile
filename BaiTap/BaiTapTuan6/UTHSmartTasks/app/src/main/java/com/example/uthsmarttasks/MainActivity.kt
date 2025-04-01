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
    val tasks = remember { mutableStateOf(listOf<Task>()) }

    when (currentScreen) {
        "List" -> ListScreen(
            tasks = tasks,
            onItemClick = { task ->
                selectedTask = task
                currentScreen = "Detail"
            },
            onEmptyList = {
                currentScreen = "ListEmpty"
            },
            onAddNew = {
                currentScreen = "AddNew"
            }
        )
        "ListEmpty" -> ListEmptyScreen(
            onAddNew = {
                currentScreen = "AddNew"
            }
        )
        "Detail" -> DetailScreen(
            task = selectedTask,
            onBack = {
                currentScreen = "List"
                selectedTask = null
            },
            onDelete = {
                tasks.value = tasks.value.filter { it.id != selectedTask?.id }
                currentScreen = "List"
                selectedTask = null
            }
        )
        "AddNew" -> AddNewScreen(
            onBack = {
                currentScreen = "List"
            },
            onAddTask = { newTask ->
                println("Adding new task: ${newTask.title}")
                tasks.value = listOf(newTask) + tasks.value
                currentScreen = "List"
            }
        )
    }
}