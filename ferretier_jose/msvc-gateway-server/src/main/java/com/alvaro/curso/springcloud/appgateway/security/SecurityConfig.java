package com.alvaro.curso.springcloud.appgateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http.authorizeExchange(authorizeExchangeSpec -> {
                    authorizeExchangeSpec
                            .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .pathMatchers(HttpMethod.GET,"/authorized/**").permitAll()
                            .pathMatchers(HttpMethod.POST, "/login").permitAll()
                            .pathMatchers("/oauth2/**", "/login/**").permitAll()
                            .pathMatchers("/api/persons/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_ROLE_EMPLEADO")
                            .pathMatchers(HttpMethod.GET,"/api/users/username/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ROLE_EMPLEADO", "ROLE_EMPLOYEE")
                            .pathMatchers("/api/products-invoices/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_ROLE_EMPLEADO")
                            .pathMatchers("/api/users/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ROLE_EMPLEADO")
                            .pathMatchers(HttpMethod.GET,"/api/warehouse/roles/user/**").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_ADMIN")
                            .pathMatchers("/api/warehouse/**").hasAuthority("ROLE_ADMIN")
                            .pathMatchers("/api/category/**").hasAnyRole("ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_ROLE_EMPLEADO")
                            .anyExchange().authenticated();
                })
                .cors(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwtSpec -> jwtSpec.jwtAuthenticationConverter(new Converter<Jwt, Mono<AbstractAuthenticationToken>>() {
                            @Override
                            public Mono<AbstractAuthenticationToken> convert(Jwt source) {
                                Collection<String> roles = source.getClaimAsStringList("roles");
                                Collection<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                                return Mono.just(new JwtAuthenticationToken(source, authorities));
                            }
                        })
                ))
                .build();
    }
}
