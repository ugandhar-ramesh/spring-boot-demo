package com.example.demo.controller;

import com.example.demo.util.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoController {

    //inject properties
    @Value("${message}")
    private String message;

    //define a private field for dependency(Auto wire it for field injection - not recommended)
    private Coach myCoach;

    private Coach anotherCoach;

    //define a constructor for dependency injection(recommended)
    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach myCoach, @Qualifier("cricketCoach") Coach anotherCoach){
//        System.out.println("In Constructor: " + getClass().getSimpleName());
        this.myCoach = myCoach;
        this.anotherCoach = anotherCoach;
    }

    //define a method for setter injection(can be any method name)

   /* @Autowired
    public void setMyCoach(Coach myCoach) {
        this.myCoach = myCoach;
    } */

    // expose "/" that return "Hello World"
    @GetMapping("/hello")
    public String sayHello() {
        return message;
    }

    @GetMapping("/check")
    public boolean checkScope() {
        return myCoach == anotherCoach;
    }

    @GetMapping("/workout")
    public String getWorkout() {
        return myCoach.getDailyWorkout();
    }
}
