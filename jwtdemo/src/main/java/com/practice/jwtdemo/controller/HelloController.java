package com.practice.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String helloWorld(@RequestParam(value = "name", defaultValue = "world") String name) {
        return "Hello " + name + "!!";
    }
}
