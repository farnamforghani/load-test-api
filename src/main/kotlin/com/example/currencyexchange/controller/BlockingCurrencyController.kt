package com.example.currencyexchange.controller

import com.example.currencyexchange.ApiResponse
import com.example.currencyexchange.service.MockCurrencyService
import org.springframework.util.StopWatch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/blocking")
class BlockingCurrencyController(
    private val mockService: MockCurrencyService
) {

    @GetMapping("/rate")
    fun getCurrencyRate(
//        @RequestParam base: String,
//        @RequestParam target: String
    ): ApiResponse<String?> {
        MonitoringController.incrementBlocking()
        val stopWatch = StopWatch()
        stopWatch.start()

        try {
            // Simulate external API call
            val rate = mockService.simulateExternalApiCallBlocking()

            stopWatch.stop()
            return ApiResponse(
                data = rate,
                responseTime = stopWatch.totalTimeMillis,
                threadName = Thread.currentThread().name
            )
        } catch (e: Exception) {
            stopWatch.stop()
            throw RuntimeException("Failed to get currency rate", e)
        }
    }

    @GetMapping("/local")
    fun localApiCall(): String {
        return "Hello world"
    }
}
