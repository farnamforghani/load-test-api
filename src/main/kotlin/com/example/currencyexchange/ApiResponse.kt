package com.example.currencyexchange

data class ApiResponse<T> (
    val data: T,
    val responseTime: Long,
    val threadName: String
)