package com.example.currencyexchange.controller

import com.example.currencyexchange.ApiResponse
import com.example.currencyexchange.service.MockCurrencyService
import org.springframework.util.StopWatch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import kotlin.random.Random
import java.time.Duration

@RestController
@RequestMapping("/api/webflux")
class WebFluxCurrencyController(
    private val mockService: MockCurrencyService
) {

    @GetMapping("/rate")
    fun getCurrencyRate(): Mono<ApiResponse<String?>> {
        MonitoringController.incrementWebflux()
        val stopWatch = StopWatch()
        stopWatch.start()

        return simulateReactiveApiCall()
            .map { rate ->
                stopWatch.stop()
                ApiResponse(
                    data = rate,
                    responseTime = stopWatch.totalTimeMillis,
                    threadName = Thread.currentThread().name
                )
            }
            .onErrorMap { e ->
                stopWatch.stop()
                RuntimeException("Failed to get currency rate", e)
            }
    }

    private fun simulateReactiveApiCall(): Mono<String?> {
        return mockService.simulateExternalApiCallReactive()
            .delayElement(Duration.ofMillis(0))
    }
}
