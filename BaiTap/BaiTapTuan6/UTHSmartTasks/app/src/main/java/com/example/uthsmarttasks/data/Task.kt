package com.example.uthsmarttasks.data

data class ApiResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val category: String,
    val dueDate: String,
    val createdAt: String,
    val updatedAt: String,
    val subtasks: List<Subtask>,
    val attachments: List<Attachment> // Thêm trường attachments
)

data class Subtask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean, // pending hoặc completed
    val status: String
)

data class Attachment(
    val id: Int,
    val fileName: String,
    val fileUrl: String
)