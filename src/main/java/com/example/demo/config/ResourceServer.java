package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * resource server with security mappings
 */
@Configuration
public class ResourceServer //extends ResourceServerConfigurerAdapter
{
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    @Order(2)
    SecurityFilterChain resourceFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults())
                .csrf(config -> config.disable())
                .authorizeHttpRequests(oauth->oauth.requestMatchers("/statistics*").hasRole("ADMIN"))
                .authorizeHttpRequests(oauth -> oauth.anyRequest().authenticated())
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
        return http.build();
    }
}