
package com.example.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.client.filter.ClientRequestFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
          .csrf(csrf -> csrf.disable())
          .addFilterAfter(new ClientRequestFilter(), BasicAuthenticationFilter.class)
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/client/call").authenticated()
              .anyRequest().permitAll()
          )
          .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }
}
