package com.agk.jdbcsqlserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/")
    String home(){
        return "Welcome to the Hello World";
    }
}