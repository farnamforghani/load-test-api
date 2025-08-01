package com.example.currencyexchange.service

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull
import reactor.core.publisher.Mono

@Service
class MockCurrencyService {

    fun simulateExternalApiCallBlockingRest(): String {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        val entity = HttpEntity<String>(headers)

        val response = restTemplate.exchange(
            "http://localhost:8081/local",
            HttpMethod.GET,
            entity,
            String::class.java
        )

        return response.body.toString()
    }


    fun simulateExternalApiCallBlockingWebClient(): String {
        val webClient = WebClient.create()

        val response = webClient
            .get()
            .uri("http://localhost:8081/local")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        return response ?: throw RuntimeException("No response received")
    }


    suspend fun simulateExternalApiCallCoroutines(): String {
        val webClient = WebClient.create()
        return webClient
            .get()
            .uri("http://localhost:8081/local")
            .retrieve()
            .awaitBodyOrNull<String>() ?: throw RuntimeException("No response received")
    }

    fun simulateExternalApiCallReactive(): Mono<String?> {
        val webClient = WebClient.create()

        return webClient
            .get()
            .uri("http://localhost:8081/local")
            .retrieve()
            .bodyToMono(String::class.java)
    }

}

/*
@GetMapping("/local")
    fun localApiCall(): ResponseEntity<String> {
        Thread.sleep(500)
        return ResponseEntity.ok("Hello World")
    }
*/