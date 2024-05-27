package com.zeven.movie_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {



    @Value("${movie.api.config.base.url}")
    private String baseUrl;

    @Value("${movie.api.config.version}")
    private String apiVersion;

    @Bean
    public WebClient webClient() {
        String url = String.format("%s/%s", baseUrl, apiVersion);
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
