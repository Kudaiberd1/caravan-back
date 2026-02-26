package com.example.caravan.service.impl;

import com.example.caravan.dto.request.ReportRequest;
import com.example.caravan.dto.request.ReportUpdateRequest;
import com.example.caravan.dto.response.EmployeeResponse;
import com.example.caravan.dto.response.ReportResponse;
import com.example.caravan.entity.Employee;
import com.example.caravan.entity.FeedbackReport;
import com.example.caravan.entity.ReportRecipient;
import com.example.caravan.entity.enums.RecipientStatusEnum;
import com.example.caravan.repository.EmployeeRepository;
import com.example.caravan.repository.FeedbackReportRepository;
import com.example.caravan.repository.ReportRecipientRepository;
import com.example.caravan.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final FeedbackReportRepository feedbackReportRepository;
    private final ReportRecipientRepository reportRecipientRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<ReportResponse> getReports() {
        List<FeedbackReport> feedbackReports = feedbackReportRepository.findByDeletedFalse();
        List<ReportResponse> reportResponses = new ArrayList<>();

        for(FeedbackReport report : feedbackReports) {

            List<ReportRecipient> reportRecipients = reportRecipientRepository.findAllByReport(report);

            List<EmployeeResponse> employeeResponses = new ArrayList<>();

            for(ReportRecipient recipient : reportRecipients) {
                employeeResponses.add(
                    EmployeeResponse.builder()
                        .employeeId(Long.valueOf(recipient.getRecipient().getEmployeeId()))
                        .fullName(recipient.getRecipient().getFullName())
                        .build()
                );
            }

            ReportResponse response = ReportResponse.builder()
                            .id(Long.valueOf(report.getReportId()))
                            .reportType(report.getReportType().toString())
                            .subject(report.getSubject())
                            .bodyText(report.getBodyText())
                            .recipients(employeeResponses)
                            .lastModifiedDate(report.getLastModifiedDate())
                            .build();


            reportResponses.add(response);
        }

        return reportResponses;
    }

    @Override
    @Transactional
    public void postReport(ReportRequest reportRequest) {
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        UUID userId = UUID.fromString(authentication.getToken().getSubject());

        FeedbackReport feedbackReport = FeedbackReport.builder()
                .senderId(userId)
                .sendDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .reportType(reportRequest.getReportType())
                .subject(reportRequest.getSubject())
                .bodyText(reportRequest.getBodyText())
                .deleted(false)
                .build();

        feedbackReportRepository.save(feedbackReport);

        List<ReportRecipient> reportRecipients = new ArrayList<>();
        for(Integer recipientId : reportRequest.getRecipientIds()) {
            Employee employee = employeeRepository.findById(recipientId).orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + recipientId));

            ReportRecipient reportRecipient = ReportRecipient.builder()
                    .report(feedbackReport)
                    .recipient(employee)
                    .status(RecipientStatusEnum.DELIVERED)
                    .build();

            reportRecipients.add(reportRecipient);
        }
        reportRecipientRepository.saveAll(reportRecipients);
    }

    @Override
    public void resolveReport(Integer id) {
        FeedbackReport feedback = feedbackReportRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Report not found with id: " + id));
        feedback.setDeleted(true);
        feedbackReportRepository.save(feedback);

    }

    @Override
    @Transactional
    public void updateReport(ReportUpdateRequest reportRequest, Integer id) {
        FeedbackReport feedbackReport = feedbackReportRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Report not found with id: " + id));

        if(reportRequest.getBodyText() != null) feedbackReport.setBodyText(reportRequest.getBodyText());
        if(reportRequest.getSubject() != null) feedbackReport.setSubject(reportRequest.getSubject());
        feedbackReport.setLastModifiedDate(OffsetDateTime.now());

        if(reportRequest.getDeleteRecipientIds() != null) {
            List<ReportRecipient> reportRecipients = new ArrayList<>();
            for(Integer recipientId : reportRequest.getDeleteRecipientIds()){
                ReportRecipient reportRecipient = reportRecipientRepository.findReportRecipientByRecipient_EmployeeIdAndReport(recipientId, feedbackReport).orElseThrow(() -> new IllegalArgumentException("Report recipient not found with employee id: " + recipientId + " and report id: " + id));
                reportRecipients.add(reportRecipient);
            }
            reportRecipientRepository.deleteAll(reportRecipients);
        }
    }
}
