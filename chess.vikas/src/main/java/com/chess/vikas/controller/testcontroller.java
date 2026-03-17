package com.chess.vikas.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/test")
public class testcontroller {

    @GetMapping("/protected")
    public String protectedApi() {
        return "You accessed protected API";
    }
}