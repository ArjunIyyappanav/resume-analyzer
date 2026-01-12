package com.arjun.resume_analyzer.dto;

public class resume {
    private String ResumeText;
    private String CandidateName;
    private String Role;

    public String getResumeText() {
        return ResumeText;
    }

    public void setResumeText(String ResumeText) {
        this.ResumeText = ResumeText;
    }

    public String getCandidateName() {
        return CandidateName;
    }

    public void setCandidateName(String CandidateName){
        this.CandidateName = CandidateName;
    }

    public String getRole(){
        return Role;
    }

    public void setRole(String Role){
        this.Role = Role;
    }   
}
