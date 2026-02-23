package com.alvaro.springcloud.msvc.info.person.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

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
