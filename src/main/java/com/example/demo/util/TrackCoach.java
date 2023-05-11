package com.example.demo.util;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TrackCoach implements Coach{

  /*  public TrackCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    } */

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k.";
    }
}
