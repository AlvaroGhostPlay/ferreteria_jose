package com.alvaro.curso.springcloud.appgateway.filters;

import org.slf4j.LoggerFactory;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class SampleGlobalFilter implements GlobalFilter, Ordered{

    private final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Ejecutando el filtro antes del request");

        //aque pidemos agregar cosas a los headers
        var request = exchange.getRequest().mutate()
                .header("token", "12345")
                .build();

        var exchangeMutado = exchange.mutate()
                .request(request)
                .build();

        return chain.filter(exchangeMutado).then(Mono.fromRunnable(()->{
            String token = exchangeMutado.getRequest().getHeaders().getFirst("token");
            logger.info("token: " + token);
            logger.info("Ejecutando el filtro despues del response");

            Optional.ofNullable(exchangeMutado.getRequest().getHeaders().getFirst("token")).ifPresent(value -> {
                logger.info("Valor del header token en el response: " + value);
                exchangeMutado.getResponse().getHeaders().add("token", value);
            });

            exchangeMutado.getResponse().addCookie(ResponseCookie.from("color", "red").build());
            exchangeMutado.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        }));
    }
    @Override
    public int getOrder() {
        //es recomendable hacer esto
        return 100;
    }

}
