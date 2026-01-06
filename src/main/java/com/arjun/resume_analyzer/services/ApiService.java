package com.arjun.resume_analyzer.services;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    public String analyzeResumeService(String resume) {
        return "Resume analysis result : Godd job!";
    }
}
