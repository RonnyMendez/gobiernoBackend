package com.backend.gobierno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Nueva forma de deshabilitar CSRF en 6.1
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/features").permitAll() // Permitir acceso público a /api/features
                        .anyRequest().authenticated()); // Requerir autenticación para las demás rutas
        return http.build();
    }
}
