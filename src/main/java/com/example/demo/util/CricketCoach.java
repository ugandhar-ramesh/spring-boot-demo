package com.example.demo.util;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Scope("prototype")
public class CricketCoach implements Coach {

  /*  public CricketCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    } */

    @Override
    public String getDailyWorkout() {
        return "Practice Bowling";
    }
}
