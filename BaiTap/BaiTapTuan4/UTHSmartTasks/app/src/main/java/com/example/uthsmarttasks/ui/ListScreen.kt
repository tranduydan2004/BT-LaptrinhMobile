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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.data.ApiClient
import com.example.uthsmarttasks.data.ApiResponse
import com.example.uthsmarttasks.data.Task
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(onItemClick: (Task?) -> Unit, onEmptyList: () -> Unit) {
    var apiResponse by remember { mutableStateOf<ApiResponse?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = ApiClient.apiService.getTasks()
                apiResponse = response
                if(response.data.isEmpty()) {
                    onEmptyList()
                }
            } catch (e: Exception) {
                error = "Failed to load tasks: ${e.message}"
                apiResponse = null
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "SmartTasks",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFF2196F3) // Màu xanh dương
                        )
                        Text(
                            text = "A simple and efficient to-do app",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF2196F3) // Màu xanh dương
                        )
                    }
                },
                navigationIcon = {
                    // Logo UTH
                    Box(modifier = Modifier
                        .background(Color(0xFFD7E8FF))
                        .clip(RoundedCornerShape(64.dp)) // Góc tròn
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_gtvt), // Thay bằng ID hình ảnh logo thực tế
                            contentDescription = "UTH Logo",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(16.dp)
                        )
                    }

                },
                actions = {
                    IconButton(onClick = { /* Xử lý thông báo */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color(0xFFFF9800) // Màu cam
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
                    .clip(RoundedCornerShape(16.dp)) // Góc tròn
                    .shadow(4.dp), // Thêm bóng
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
                            contentDescription = "Home"
                        )
                    }
                    IconButton(onClick = { /* Xử lý Calendar */ }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Calendar"
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f)) // Đẩy FAB vào giữa

                    IconButton(onClick = { /* Xử lý Document */ }) {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = "Document"
                        )
                    }
                    IconButton(onClick = { /* Xử lý Settings */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Xử lý Add */ },
                modifier = Modifier
                    .size(56.dp)
                    .offset(y = (50).dp), // Nâng FAB lên để nổi trên BottomAppBar
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center // Đặt FAB ở giữa

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            when {
                error != null -> {
                    Text(text = error ?: "Unknown error", color = MaterialTheme.colorScheme.error)
                    Button(
                        onClick = {
                            scope.launch {
                                try {
                                    val response = ApiClient.apiService.getTasks()
                                    apiResponse = response
                                    error = null
                                } catch (e: Exception) {
                                    error = "Failed to load tasks: ${e.message}"
                                    apiResponse = null
                                }
                            }
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Retry")
                    }
                }
                apiResponse == null -> {
                    Text(text = "Loading...")
                }
                apiResponse?.data?.isEmpty() == true -> {
                    onItemClick(null) // Chuyển sang List Empty
                }
                else -> {
                    LazyColumn {
                        items(apiResponse?.data ?: emptyList()) { task ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable { onItemClick(task) },
                                colors = CardDefaults.cardColors(
                                    containerColor = when (task.status) {
                                        "In Progress" -> Color(0xFFFFD7D7) // Màu hồng nhạt
                                        "Pending" -> Color(0xFFD7E8FF) // Màu xanh nhạt
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
                                                color = Color.Black
                                            )
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = "Status: ${task.status}",
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                color = Color.Black,
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = task.dueDate.substring(11, 16) + " " + task.dueDate.substring(0, 10),
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = Color.Black
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
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    MaterialTheme {
        ListScreen(
            onItemClick = {},
            onEmptyList = {}
        )
    }
}