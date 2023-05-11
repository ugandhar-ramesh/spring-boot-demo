package com.example.demo.config;

import com.example.demo.util.Coach;
import com.example.demo.util.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    //bean id defaults to method name if aquatic is not mentioned
    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
