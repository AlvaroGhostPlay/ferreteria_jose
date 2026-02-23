package com.alvaro.springcloud.msvc.persons.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${config.baseurl.endpoint.msvc-catalog}")
    private String url;

    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
