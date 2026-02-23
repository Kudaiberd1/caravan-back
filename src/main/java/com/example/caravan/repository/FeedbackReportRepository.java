package com.example.caravan.repository;

import com.example.caravan.entity.FeedbackReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackReportRepository extends JpaRepository<FeedbackReport, Long> {
}
