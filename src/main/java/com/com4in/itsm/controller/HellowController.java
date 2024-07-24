package com.com4in.itsm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class HellowController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }
}
