package com.server.scarlet_shade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
 
    @GetMapping("/teste")
    public String teste() {
        return "Ok";
    }
}