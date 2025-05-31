package com.javalessons.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GentleConroller {

    @GetMapping("/hello")
    public String helloPage() {
        return "gentle/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "gentle/goodbye";
    }


}
