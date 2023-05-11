package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // add support for JDBC ... no more hardcoded users :-)

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/students").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/api/students/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/api/students").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.PUT, "/api/students/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PATCH, "/api/students").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("ADMIN")
        );

        // use HTTP Basic authentication
        http.httpBasic();

        //disable Cross Site Request Forgery(CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf().disable();
        return http.build();
    }

   /* @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails ugandve = User.builder()
                .username("ugandve")
                .password("{noop}test123")
                .roles("STUDENT")
                .build();

        UserDetails ravi = User.builder()
                .username("ravi")
                .password("{noop}test123")
                .roles("STUDENT", "TEACHER")
                .build();

        UserDetails kishore = User.builder()
                .username("kishore")
                .password("{noop}test123")
                .roles("STUDENT", "TEACHER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ugandve, ravi, kishore);
    } */
}
