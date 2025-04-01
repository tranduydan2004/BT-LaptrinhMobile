package com.example.uthsmarttasks.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uthsmarttasks.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewScreen(
    onBack: () -> Unit,
    onAddTask: (Task) -> Unit
) {
    var title by remember { mutableStateOf("Do homework") }
    var description by remember { mutableStateOf("Don't give up") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Add New",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = Color(0xFF2196F3)
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF2196F3)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Task",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                placeholder = { Text("Enter task title") }
            )

            Text(
                text = "Description",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                placeholder = { Text("Enter task description") }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val newTask = Task(
                        id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt(),
                        title = title,
                        description = description,
                        status = "Pending",
                        priority = "Medium",
                        category = "Work",
                        dueDate = "2025-04-01T14:00:00Z",
                        createdAt = "2025-04-01T09:00:00Z",
                        updatedAt = "2025-04-01T09:00:00Z",
                        subtasks = emptyList(),
                        attachments = emptyList()
                    )
                    onAddTask(newTask)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text(
                    text = "Add",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewScreenPreview() {
    MaterialTheme {
        AddNewScreen(
            onBack = {},
            onAddTask = {}
        )
    }
}