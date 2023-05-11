package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component("T-Coach")
public class TennisCoach implements Coach{

  /*  public TennisCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    } */

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
