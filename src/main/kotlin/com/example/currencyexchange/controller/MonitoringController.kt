package com.example.currencyexchange.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.management.ManagementFactory
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping("/api/monitor")
class MonitoringController {

    companion object {
        private val blockingRestRequests = AtomicInteger(0)
        private val blockingWebClientRequests = AtomicInteger(0)
        private val coroutineRequests = AtomicInteger(0)
        private val webfluxRequests = AtomicInteger(0)

        fun incrementRestBlocking() = blockingRestRequests.incrementAndGet()
        fun incrementWebClientBlocking() = blockingWebClientRequests.incrementAndGet()
        fun incrementCoroutine() = coroutineRequests.incrementAndGet()
        fun incrementWebflux() = webfluxRequests.incrementAndGet()
    }

    @GetMapping("/stats")
    fun getSystemStats(): Map<String, Any> {
        val runtime = Runtime.getRuntime()
        val memoryBean = ManagementFactory.getMemoryMXBean()
        val threadBean = ManagementFactory.getThreadMXBean()

        return mapOf(
            "timestamp" to System.currentTimeMillis(),
            "requests" to mapOf(
                "blockingRest" to blockingRestRequests.get(),
                "blockingWebClient" to blockingWebClientRequests.get(),
                "coroutines" to coroutineRequests.get(),
                "webflux" to webfluxRequests.get()
            ),
            "memory" to mapOf(
                "used" to "${(runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024} MB",
                "free" to "${runtime.freeMemory() / 1024 / 1024} MB",
                "total" to "${runtime.totalMemory() / 1024 / 1024} MB",
                "max" to "${runtime.maxMemory() / 1024 / 1024} MB",
                "heap" to "${memoryBean.heapMemoryUsage.used / 1024 / 1024} MB"
            ),
            "threads" to mapOf(
                "active" to Thread.activeCount(),
                "peak" to threadBean.peakThreadCount,
                "total" to threadBean.totalStartedThreadCount
            ),
            "system" to mapOf(
                "processors" to runtime.availableProcessors(),
                "uptime" to "${ManagementFactory.getRuntimeMXBean().uptime / 1000} seconds"
            )
        )
    }

    @GetMapping("/reset")
    fun resetCounters(): Map<String, String> {
        blockingRestRequests.set(0)
        blockingWebClientRequests.set(0)
        coroutineRequests.set(0)
        webfluxRequests.set(0)
        return mapOf("status" to "Counters reset")
    }
}
