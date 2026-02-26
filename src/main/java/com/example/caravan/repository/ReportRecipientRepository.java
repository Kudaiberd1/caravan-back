package com.example.caravan.repository;

import com.example.caravan.entity.FeedbackReport;
import com.example.caravan.entity.ReportRecipient;
import com.example.caravan.entity.ReportRecipientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRecipientRepository extends JpaRepository<ReportRecipient, ReportRecipientId> {

    Optional<ReportRecipient> findReportRecipientByRecipient_EmployeeIdAndReport(Integer recipientEmployeeId, FeedbackReport report);

    List<ReportRecipient> findAllByReport(FeedbackReport report);
}
