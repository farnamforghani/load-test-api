package com.example.currencyexchange

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class PerformanceMonitoringAspect {

    private val logger = LoggerFactory.getLogger(PerformanceMonitoringAspect::class.java)

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val startTime = System.currentTimeMillis()
        val result = joinPoint.proceed()
        val executionTime = System.currentTimeMillis() - startTime

        logger.info("${joinPoint.signature.name} executed in ${executionTime}ms on thread ${Thread.currentThread().name}")

        return result
    }
}