package com.example.currencyexchange.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping("/api/metrics")
class MetricsController {

    private val requestCounts = mutableMapOf<String, AtomicInteger>()
    private val responseTimes = mutableMapOf<String, MutableList<Long>>()

    @GetMapping("/stats")
    fun getStats(): Map<String, Any> {
        return mapOf(
            "requestCounts" to requestCounts.mapValues { it.value.get() },
            "averageResponseTimes" to responseTimes.mapValues { entry ->
                entry.value.average()
            },
            "activeThreads" to Thread.activeCount(),
            "availableProcessors" to Runtime.getRuntime().availableProcessors()
        )
    }
}