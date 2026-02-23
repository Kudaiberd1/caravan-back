package com.example.caravan.repository;

import com.example.caravan.entity.FeedbackReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackReportRepository extends JpaRepository<FeedbackReport, Long> {
    List<FeedbackReport> findAllByDeletedFalseOrDeletedIsNull();
}
