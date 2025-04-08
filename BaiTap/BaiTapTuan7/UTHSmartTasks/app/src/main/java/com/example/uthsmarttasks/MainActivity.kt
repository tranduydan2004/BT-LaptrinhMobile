package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.data.DatabaseProvider
import com.example.uthsmarttasks.data.Task
import com.example.uthsmarttasks.data.TaskRepository
import com.example.uthsmarttasks.ui.*
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
                    val taskRepository = remember {
                        TaskRepository(DatabaseProvider.getDatabase(this@MainActivity).taskDao())
                    }
                    val viewModel = remember { TaskViewModel(taskRepository) }

                    // Tạo NavController cho NavigationHost
                    val navController = rememberNavController()
                    NavigationHost(navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, viewModel: TaskViewModel) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen()
            LaunchedEffect(Unit) {
                delay(2000)
                navController.navigate("onboarding") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
        composable("onboarding") {
            OnboardingScreen(
                onSkip = {
                    navController.navigate("task_graph") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                },
                onGetStarted = {
                    navController.navigate("task_graph") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }
        composable("task_graph") {
            // Tạo NavController riêng cho TaskGraph
            val taskGraphNavController = rememberNavController()
            TaskGraph(taskGraphNavController, viewModel)
        }
    }
}

@Composable
fun TaskGraph(navController: NavHostController, viewModel: TaskViewModel) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListScreen(
                viewModel = viewModel,
                onItemClick = { task ->
                    // Chỉ truyền ID của Task và gọi fetchTaskById
                    if (task != null) {
                        navController.currentBackStackEntry?.savedStateHandle?.set("taskId", task.id)
                    }
                    if (task != null) {
                        viewModel.fetchTaskById(task.id)
                    }
                    navController.navigate("detail")
                },
                onEmptyList = { navController.navigate("list_empty") },
                onAddNew = { navController.navigate("add_new") }
            )
        }
        composable("list_empty") {
            ListEmptyScreen(
                onAddNew = { navController.navigate("add_new") }
            )
        }
        composable("detail") {
            // Quan sát selectedTask từ ViewModel
            val task = viewModel.selectedTask.collectAsState().value

            DetailScreen(
                task = task,
                onBack = {
                    viewModel.clearSelectedTask() // Xóa selectedTask khi quay lại
                    navController.popBackStack()
                },
                onDelete = { taskToDelete ->
                    taskToDelete?.let { viewModel.deleteTask(it) }
                    viewModel.clearSelectedTask() // Xóa selectedTask sau khi xóa
                    navController.popBackStack()
                }
            )
        }
        composable("add_new") {
            AddNewScreen(
                onBack = { navController.popBackStack() },
                onAddTask = { newTask ->
                    viewModel.addTask(newTask)
                    navController.popBackStack()
                }
            )
        }
    }
}