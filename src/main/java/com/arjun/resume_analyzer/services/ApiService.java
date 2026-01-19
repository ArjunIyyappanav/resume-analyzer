package com.arjun.resume_analyzer.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjun.resume_analyzer.entity.ResumeAnalysis;
import com.arjun.resume_analyzer.repository.ResumeAnalysisRepository;
import com.arjun.resume_analyzer.dto.request;
import com.arjun.resume_analyzer.dto.response;

@Service
public class ApiService {
    private final ResumeAnalysisRepository repository;

    public ApiService(ResumeAnalysisRepository repository) {
        this.repository = repository;
    }

    public @ResponseBody response analyzeResumeService(long resume_id){

        ResumeAnalysis resume = repository
        .findById(resume_id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid resume ID"));

        String CandidateName = resume.getCandidateName();
        String Role = resume.getRole();
        //String ResumeText = resume.getResumeContent();

        response response_analyze_result = new response();
        response_analyze_result.setAnalysisResult("Resume ID: "+resume_id+" analyzed successfully. Good Resume");
        response_analyze_result.setCandidateName(CandidateName);
        response_analyze_result.setRole(Role);

        return response_analyze_result;
    }

    public long storeResumeService(@RequestBody request resume) {
        String candidateName = resume.getCandidateName();
        String role = resume.getRole();
        String resumeText = resume.getResumeText();

        ResumeAnalysis resumeAnalysis = new ResumeAnalysis();

        resumeAnalysis.setCandidateName(candidateName);
        resumeAnalysis.setResumeContent(resumeText);
        resumeAnalysis.setRole(role);

        ResumeAnalysis saved = repository.save(resumeAnalysis);
        return saved.getId();
    }

    public String getall(){
        StringBuffer sb=new StringBuffer();
        for(ResumeAnalysis r:repository.findAll()){
            sb.append("ID: "+r.getId()+"\n");
            sb.append("Candidate Name: "+r.getCandidateName()+"\n");
            sb.append("Role: "+r.getRole()+"\n");
            sb.append("Resume Content: "+r.getResumeContent()+"\n");
            sb.append("-------------------------------\n");
        }
        return sb.toString();
    }
}
