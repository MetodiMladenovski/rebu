package com.example.uber.service;

import com.example.uber.model.immutable.AdminReport;
import com.example.uber.model.immutable.DriverReport;
import com.example.uber.model.immutable.PassengerReport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ReportService {
    List<PassengerReport> getReportForPassenger(UUID passengerId);

    List<AdminReport> getReportForAdmin();

    List<DriverReport> getReportForDriver(UUID driverId);

    ByteArrayInputStream downloadPassengerReport(UUID passengerId) throws IOException;
    ByteArrayInputStream downloadDriverReport(UUID driverId) throws IOException;

    ByteArrayInputStream downloadAdminReport() throws IOException;

}
