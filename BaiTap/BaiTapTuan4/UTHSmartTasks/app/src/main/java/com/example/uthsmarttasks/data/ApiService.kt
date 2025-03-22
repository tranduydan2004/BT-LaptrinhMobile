package com.example.uthsmarttasks.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("researchUTH/tasks")
    suspend fun getTasks(): ApiResponse

    @GET("researchUTH/tasks/{id}")
    suspend fun getTaskById(@Path("id") id: Int): Task
}