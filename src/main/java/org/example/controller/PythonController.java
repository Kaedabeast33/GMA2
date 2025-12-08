package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/python")
public class PythonController {
    @GetMapping("/hello")
    public String helloPython() {
        return "Hello from Python Controller!";
    }

}
