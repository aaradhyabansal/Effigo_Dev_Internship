package com.aaradhya.rest.webservices.restful_web_services.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http)throws Exception
    {
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }
}
