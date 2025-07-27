package com.example.currencyexchange.controller

import com.example.currencyexchange.ApiResponse
import com.example.currencyexchange.service.MockCurrencyService
import kotlinx.coroutines.*
import org.springframework.util.StopWatch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/coroutines")
class CoroutinesCurrencyController(
    private val mockService: MockCurrencyService
) {

    @GetMapping("/rate")
    suspend fun getCurrencyRate(): ApiResponse<String?> = withContext(Dispatchers.IO) {
        MonitoringController.incrementCoroutine()
        val stopWatch = StopWatch()
        stopWatch.start()

        try {
            // Simulate external API call in a non-blocking way
            val rate = simulateAsyncApiCall()

            stopWatch.stop()
            ApiResponse(
                data = rate,
                responseTime = stopWatch.totalTimeMillis,
                threadName = Thread.currentThread().name
            )
        } catch (e: Exception) {
            stopWatch.stop()
            throw RuntimeException("Failed to get currency rate", e)
        }
    }

    private suspend fun simulateAsyncApiCall(): String {
        return withContext(Dispatchers.IO) {
            // delay(100 + Random.nextInt(200).toLong()) // Non-blocking delay
            mockService.simulateExternalApiCallCoroutines()
        }
    }
}