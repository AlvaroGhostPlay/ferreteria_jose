package com.alvaro.springcloud.msvc.oauth.services;

import com.alvaro.springcloud.msvc.oauth.models.User;
import io.micrometer.tracing.Tracer;
import jakarta.ws.rs.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private Tracer tracer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Ingresando al proceso Login UserService::loadUserByUsername con {}", username);
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        try{
            User user = webClientBuilder.build()
                    .get()
                    .uri("http://MSVC-USERS/username/{username}", username)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
            Set<SimpleGrantedAuthority> roles = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                    .collect(Collectors.toSet());
            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.isEnabled(),
                    true,
                    true,
                    true,
                    roles);
            String message = "Se ha encontrado el usuario con exito";
            logger.info( message+ " {}", user);
            tracer.currentSpan().tag("success.login", message + " " + user.getUsername());
            return userDetails;
        }catch (WebClientResponseException.NotFound e){
            throw new UsernameNotFoundException("Usuario no existe: " + username, e);

        }catch (WebClientResponseException e){
            throw new BadCredentialsException("Credenciales inválidas", e);
        } catch (Exception e) {
            throw new RuntimeException("Algo paso que no lo veo"+e);
        }
    }
}
