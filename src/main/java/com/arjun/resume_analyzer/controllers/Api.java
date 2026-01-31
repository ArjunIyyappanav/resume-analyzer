package com.arjun.resume_analyzer.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arjun.resume_analyzer.dto.request;
import com.arjun.resume_analyzer.services.ApiService;
import com.arjun.resume_analyzer.dto.response;
import com.arjun.resume_analyzer.entity.ResumeAnalysis;
import com.arjun.resume_analyzer.dto.resultresponse;

@RestController
@RequestMapping("/api")
public class Api {

    private final ApiService api;

    public Api(ApiService api) {
        this.api = api;
    }

    //GET Request
    //Need Resume ID to fetch Resume Details
    //Stored in H2 Database
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/resume/{id}")
    public @ResponseBody response getResumeById(@PathVariable long id) {
        return api.getResumeService(id);
    }
    
    //GET Request
    //Analyze Resume by ID
    //Returns Analysis Result
    //Stores Analysis in H2 Database
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/resume/{id}/analyze")
    public @ResponseBody resultresponse analyzeResume(@PathVariable long id) {
        return api.analyzeResumeService(id);
    }

    //POST Request
    //Upload Resume File
    //PDF or DOCX formats supported
    //Stores Resume in uploads folder
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/resumes/upload")
    public String analyzeResumePost(@RequestParam("file") MultipartFile resume) {
        String filePath = System.getProperty("user.dir") + "/uploads/" + resume.getOriginalFilename();
        String fileUploadStatus;
        long resumeId = -1;
        try{

            FileOutputStream fos = new FileOutputStream(new File(filePath));
            String resumeContent = new String(resume.getBytes());
            fos.write(resumeContent.getBytes());
            fos.close();

            resumeId = api.storeResumeService(resumeContent);

            fileUploadStatus = "File uploaded successfully: " + filePath;
        }catch(Exception e){
            fileUploadStatus = "File upload failed: " + e.getMessage();
        }

        return fileUploadStatus;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getresumes")
    public ResumeAnalysis[] getresumes(){
        return api.getall();
    }

}
