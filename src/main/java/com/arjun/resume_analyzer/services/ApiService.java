package com.arjun.resume_analyzer.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjun.resume_analyzer.entity.ResumeAnalysis;
import com.arjun.resume_analyzer.repository.ResumeAnalysisRepository;
import com.arjun.resume_analyzer.dto.request;
import com.arjun.resume_analyzer.dto.response;
import com.arjun.resume_analyzer.dto.resultresponse;

@Service
public class ApiService {
    private final ResumeAnalysisRepository repository;

    public ApiService(ResumeAnalysisRepository repository) {
        this.repository = repository;
    }

    public @ResponseBody resultresponse analyzeResumeService(long resume_id){

        ResumeAnalysis resume = repository
        .findById(resume_id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid resume ID"));

        long resumeId = resume.getId();
        int resumeScore = resume.getScore();
        String resumeFeedback = resume.getFeedback();
        

        resultresponse response_result = new resultresponse();
        response_result.setId(resumeId);
        response_result.setScore(resumeScore);
        response_result.setFeedback(resumeFeedback);
        return response_result;
    }

    public @ResponseBody response getResumeService(long resume_id){

        ResumeAnalysis resume = repository
        .findById(resume_id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid resume ID"));

        String CandidateName = resume.getCandidateName();
        String Role = resume.getRole();
        String ResumeText = resume.getResumeContent();

        response response_get_result = new response();
        response_get_result.setCandidateName(CandidateName);
        response_get_result.setRole(Role);
        response_get_result.setResumeText(ResumeText);

        return response_get_result;
    }

    public long storeResumeService(@RequestBody String resumeContent) {
        //Analyze the resume content using AI (Simulated here)
        String candidateName = "John Doe";  
        String role = "Software Engineer";
        String resumeText = resumeContent;

        ResumeAnalysis resumeAnalysis = new ResumeAnalysis();

        resumeAnalysis.setCandidateName(candidateName);
        resumeAnalysis.setResumeContent(resumeText.substring(0,100));
        resumeAnalysis.setRole(role);

        //AI Analysis Simulation
        resumeAnalysis.setScore(85);
        resumeAnalysis.setFeedback("Well-structured resume with relevant experience.");

        ResumeAnalysis saved = repository.save(resumeAnalysis);

        resumeAnalysis.setId(saved.getId());

        return saved.getId();
    }

    public ResumeAnalysis[] getall(){
        ResumeAnalysis resumes[] = new ResumeAnalysis[100];
        int a = 0;
        for(ResumeAnalysis r : repository.findAll()){
            resumes[a++] = r;
        }
        return resumes;
    }
}
