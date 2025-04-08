package com.example.uthsmarttasks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.data.Task
import com.example.uthsmarttasks.data.TaskDao
import com.example.uthsmarttasks.data.TaskRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: TaskViewModel,
    onItemClick: (Task?) -> Unit,
    onEmptyList: () -> Unit,
    onAddNew: () -> Unit
) {
    val tasks = viewModel.tasks.collectAsState().value

    LaunchedEffect(tasks) {
        if (tasks.isEmpty()) {
            onEmptyList()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "SmartTasks",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFF2196F3)
                        )
                        Text(
                            text = "A simple and efficient to-do app",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFD7E8FF))
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_gtvt),
                            contentDescription = "UTH Logo",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(8.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Xử lý thông báo */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color(0xFFFF9800)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(4.dp),
                containerColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* Xử lý Home */ }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = { /* Xử lý Calendar */ }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Calendar",
                            tint = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* Xử lý Document */ }) {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = "Document",
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = { /* Xử lý Settings */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.Gray
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    println("FAB clicked in ListScreen")
                    onAddNew()
                },
                modifier = Modifier
                    .size(56.dp)
                    .offset(y = (50).dp),
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn {
                items(tasks) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onItemClick(task) },
                        colors = CardDefaults.cardColors(
                            containerColor = when (task.status) {
                                "In Progress" -> Color(0xFFFFD7D7)
                                "Pending" -> Color(0xFFD7E8FF)
                                else -> Color.White
                            }
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = task.status == "Completed",
                                onCheckedChange = { /* Xử lý thay đổi trạng thái */ },
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = task.title,
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = task.description,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color.Gray
                                    )
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        text = "Status: ${task.status}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = task.dueDate.substring(11, 16) + " " + task.dueDate.substring(0, 10),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ListScreenPreview() {
//    MaterialTheme {
//        ListScreen(
//            viewModel = TaskViewModel(TaskRepository(object : TaskDao {
//                override fun getAllTasks(): Flow<List<Task>> {
//                    TODO("Not yet implemented")
//                }
//
//                override suspend fun insertTask(task: Task) {
//                    TODO("Not yet implemented")
//                }
//
//                override suspend fun updateTask(task: Task) {
//                    TODO("Not yet implemented")
//                }
//
//                override suspend fun deleteTask(task: Task) {
//                    TODO("Not yet implemented")
//                }
//            })),
//            onItemClick = {},
//            onEmptyList = {},
//            onAddNew = {}
//        )
//    }
//}