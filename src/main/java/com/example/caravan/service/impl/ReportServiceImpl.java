package com.example.caravan.service.impl;

import com.example.caravan.dto.response.EmployeeResponse;
import com.example.caravan.dto.response.ReportResponse;
import com.example.caravan.entity.FeedbackReport;
import com.example.caravan.entity.ReportRecipient;
import com.example.caravan.repository.FeedbackReportRepository;
import com.example.caravan.repository.ReportRecipientRepository;
import com.example.caravan.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final FeedbackReportRepository feedbackReportRepository;
    private final ReportRecipientRepository reportRecipientRepository;

    @Override
    public List<ReportResponse> getReports() {
        List<FeedbackReport> feedbackReports = feedbackReportRepository.findAll();
        List<ReportResponse> reportResponses = new ArrayList<>();

        for(FeedbackReport report : feedbackReports) {

            List<ReportRecipient> reportRecipients = reportRecipientRepository.findAllByReport(report);

            List<EmployeeResponse> employeeResponses = new ArrayList<>();

            for(ReportRecipient recipient : reportRecipients) {
                employeeResponses.add(
                    EmployeeResponse.builder()
                        .employeeId(recipient.getRecipient().getEmployeeId())
                        .fullName(recipient.getRecipient().getFullName())
                        .build()
                );
            }

            ReportResponse response = ReportResponse.builder()
                            .id(report.getReportId())
                            .reportType(report.getReportType().toString())
                            .bodyText(report.getBodyText())
                            .recipients(employeeResponses)
                            .lastModifiedDate(report.getLastModifiedDate())
                            .build();


            reportResponses.add(response);
        }

        return reportResponses;
    }
}
