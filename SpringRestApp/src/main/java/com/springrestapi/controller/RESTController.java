package com.springrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // чтобы не писать ResponseBody каждому методу
@RequestMapping("/api")
public class RESTController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello World";
    }
}
