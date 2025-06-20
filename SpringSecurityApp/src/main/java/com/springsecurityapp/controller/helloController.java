package com.springsecurityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helloController {
    @GetMapping("/hello")
    String hello() {
        return "hello";
    }
}
