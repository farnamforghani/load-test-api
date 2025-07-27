package com.example.currencyexchange

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebConfig {
    @Bean
    fun restTemplate(): RestTemplate {
        val factory = HttpComponentsClientHttpRequestFactory()
        factory.setConnectTimeout(5000)
        factory.setReadTimeout(5000)
        return RestTemplate(factory)
    }

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .clientConnector(
                ReactorClientHttpConnector(
                HttpClient.create()
                    .responseTimeout(Duration.ofSeconds(5))
            )
            )
            .build()
    }
}