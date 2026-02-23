package com.example.caravan.repository;

import com.example.caravan.entity.FeedbackReport;
import com.example.caravan.entity.ReportRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRecipientRepository extends JpaRepository<ReportRecipient, Long> {
    List<ReportRecipient> findAllByReport(FeedbackReport report);
}
