package com.arjun.resume_analyzer.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.arjun.resume_analyzer.entity.ResumeAnalysis;

public interface ResumeAnalysisRepository extends JpaRepository<ResumeAnalysis, Long> {
}
