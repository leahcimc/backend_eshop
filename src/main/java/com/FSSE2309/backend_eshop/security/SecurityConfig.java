package com.FSSE2309.backend_eshop.security;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Elements.ANONYMOUS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/error", "/error/**", "/public/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/error", "/error/**", "/public/**").permitAll())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                //.authorizeHttpRequests(auth -> auth.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll())
                .csrf(csrf -> csrf.disable());
        http
                .oauth2ResourceServer(
                        oauth2ResourceServer -> oauth2ResourceServer.jwt(
                                jwt -> jwt.decoder(
                                        JwtDecoders.fromIssuerLocation(issuer))
                        )
                );
        return http.build();
    }
}
