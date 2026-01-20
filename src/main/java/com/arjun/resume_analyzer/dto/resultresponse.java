package com.arjun.resume_analyzer.dto;

public class resultresponse {
    private long resumeId;
    private int score;
    private String feedback;

    public long getResumeId() {
        return resumeId;
    }   
    
    public void setId(long resumeId) {
        this.resumeId = resumeId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
