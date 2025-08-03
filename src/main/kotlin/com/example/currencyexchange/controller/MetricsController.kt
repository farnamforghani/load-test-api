package com.example.currencyexchange.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.management.ManagementFactory

@RestController
@RequestMapping("/metrics")
class MetricsController {

    @GetMapping
    fun getMetrics(): Map<String, Any> {
        val runtime = Runtime.getRuntime()
        val threadMXBean = ManagementFactory.getThreadMXBean()

        return mapOf(
            "activeThreads" to threadMXBean.threadCount,
            "totalMemory" to runtime.totalMemory(),
            "freeMemory" to runtime.freeMemory(),
            "maxMemory" to runtime.maxMemory()
        )
    }
}