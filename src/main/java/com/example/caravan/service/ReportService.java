package com.example.caravan.service;

import com.example.caravan.dto.request.ReportRequest;
import com.example.caravan.dto.response.ReportResponse;

import java.util.List;

public interface ReportService {

    List<ReportResponse> getReports();

    void postReport(ReportRequest reportRequest);
}
