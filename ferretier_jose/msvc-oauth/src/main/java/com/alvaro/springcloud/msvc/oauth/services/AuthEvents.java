package com.alvaro.springcloud.msvc.oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthEvents {
    private static final Logger log = LoggerFactory.getLogger(AuthEvents.class);

    @EventListener
    public void success(org.springframework.security.authentication.event.AuthenticationSuccessEvent e) {
        log.info("LOGIN OK: {}", e.getAuthentication().getName());
    }

    @EventListener
    public void fail(org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent e) {
        log.warn("LOGIN FAIL (bad credentials): {}", e.getAuthentication().getName());
    }
}
