package com.example.demo.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @PostMapping("/greet")
    public String greet(@RequestBody GreetRequest request) {
        return "Hello, " + request.getName() + "! You are " + request.getAge() + " years old.";
    }
}

@Setter
@Getter
class GreetRequest {
    private String name;
    private int age;

}