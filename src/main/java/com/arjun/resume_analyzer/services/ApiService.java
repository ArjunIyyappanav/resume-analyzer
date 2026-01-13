package com.arjun.resume_analyzer.services;

import org.springframework.stereotype.Service;

import com.arjun.resume_analyzer.entity.ResumeAnalysis;
import com.arjun.resume_analyzer.repository.ResumeAnalysisRepository;
@Service
public class ApiService {

    // ✅ FIELD goes here (class level)
    private final ResumeAnalysisRepository repository;

    // ✅ Constructor injection (BEST PRACTICE)
    public ApiService(ResumeAnalysisRepository repository) {
        this.repository = repository;
    }

    // Simple version (optional)
    public String analyzeResumeService(String resume) {
        return "Resume analysis result : Good job!";
    }

    // Main persistence method
    public String analyzeResumeService(String resumeText,
                                       String candidateName,
                                       String role) {

        ResumeAnalysis resumeAnalysis = new ResumeAnalysis();

        resumeAnalysis.setCandidateName(candidateName);
        resumeAnalysis.setResumeContent(resumeText);
        resumeAnalysis.setRole(role);

        // Save to DB
        ResumeAnalysis saved = repository.save(resumeAnalysis);

        return "Resume analysis saved with id: " + saved.getId();
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
