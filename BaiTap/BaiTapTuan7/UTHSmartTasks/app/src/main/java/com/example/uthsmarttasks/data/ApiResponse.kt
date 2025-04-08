package com.example.uthsmarttasks.data

data class ApiResponse(
    val tasks: List<Task>,
    val status: String,
    val message: String? = null
)