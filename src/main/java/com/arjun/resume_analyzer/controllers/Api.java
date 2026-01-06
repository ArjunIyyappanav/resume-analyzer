package com.arjun.resume_analyzer.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arjun.resume_analyzer.services.ApiService;
@RestController
@RequestMapping("/api")
public class Api {

    private final ApiService api;

    public Api(ApiService api) {
        this.api = api;
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/analyze")
    public String analyzeResume(@RequestBody String resume) {
        return api.analyzeResumeService(resume);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/analyze")
}
