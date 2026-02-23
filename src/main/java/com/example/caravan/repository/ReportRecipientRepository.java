package com.example.caravan.repository;

import com.example.caravan.entity.FeedbackReport;
import com.example.caravan.entity.ReportRecipient;
import com.example.caravan.entity.ReportRecipientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ReportRecipientRepository extends JpaRepository<ReportRecipient, ReportRecipientId> {
    List<ReportRecipient> findAllByReport(FeedbackReport report);

    @Query("SELECT rr FROM ReportRecipient rr JOIN FETCH rr.recipient WHERE rr.report IN :reports")
    List<ReportRecipient> findAllWithRecipientByReportIn(@Param("reports") Collection<FeedbackReport> reports);
}
