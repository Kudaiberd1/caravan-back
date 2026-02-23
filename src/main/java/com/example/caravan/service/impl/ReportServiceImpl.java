package com.example.caravan.service.impl;

import com.example.caravan.dto.response.ReportRecipientResponse;
import com.example.caravan.dto.response.ReportResponse;
import com.example.caravan.entity.FeedbackReport;
import com.example.caravan.repository.FeedbackReportRepository;
import com.example.caravan.repository.ReportRecipientRepository;
import com.example.caravan.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final FeedbackReportRepository feedbackReportRepository;
    private final ReportRecipientRepository reportRecipientRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReportResponse> getReports() {
        List<FeedbackReport> feedbackReports = feedbackReportRepository.findAllByDeletedFalseOrDeletedIsNull();

        Map<Long, List<ReportRecipientResponse>> recipientsByReportId = reportRecipientRepository
                .findAllWithRecipientByReportIn(feedbackReports)
                .stream()
                .collect(Collectors.groupingBy(
                        rr -> rr.getReport().getReportId(),
                        Collectors.mapping(rr -> ReportRecipientResponse.builder()
                                .employeeId(rr.getRecipient().getEmployeeId())
                                .fullName(rr.getRecipient().getFullName())
                                .status(rr.getStatus())
                                .build(),
                                Collectors.toList())
                ));

        return feedbackReports.stream()
                .map(report -> ReportResponse.builder()
                        .id(report.getReportId())
                        .reportType(report.getReportType().toString())
                        .subject(report.getSubject())
                        .bodyText(report.getBodyText())
                        .sendDate(report.getSendDate())
                        .lastModifiedDate(report.getLastModifiedDate())
                        .recipients(recipientsByReportId.getOrDefault(report.getReportId(), List.of()))
                        .build())
                .collect(Collectors.toList());
    }
}
