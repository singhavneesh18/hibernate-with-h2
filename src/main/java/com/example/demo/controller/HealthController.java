package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthController")
public class HealthController {

    @GetMapping(value = "/checkHealth")
    public String checkHealth() {
        return "OK";
    }
}
