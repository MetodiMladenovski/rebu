package com.example.uber.service.impl;

import com.example.uber.model.filegenerators.AdminPDFExporter;
import com.example.uber.model.filegenerators.DriverPDFExporter;
import com.example.uber.model.filegenerators.PassengerPDFExporter;
import com.example.uber.model.immutable.AdminReport;
import com.example.uber.model.immutable.DriverReport;
import com.example.uber.model.immutable.PassengerReport;
import com.example.uber.repository.immutable.AdminReportRepository;
import com.example.uber.repository.immutable.DriverReportRepository;
import com.example.uber.repository.immutable.PassengerReportRepository;
import com.example.uber.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PassengerReportRepository passengerReportRepository;
    private final AdminReportRepository adminReportRepository;
    private final DriverReportRepository driverReportRepository;

    @Override
    public ByteArrayInputStream downloadPassengerReport(UUID passengerId) {
        List<PassengerReport> passengerReport = getReportForPassenger(passengerId);
        PassengerPDFExporter exporter = new PassengerPDFExporter(passengerReport);
        return exporter.export();
    }

    @Override
    public List<PassengerReport> getReportForPassenger(UUID passengerId) {
        return passengerReportRepository.findByPassengerId(passengerId);
    }

    @Override
    public ByteArrayInputStream downloadDriverReport(UUID driverId) {
        List<DriverReport> driverReport = getReportForDriver(driverId);
        DriverPDFExporter exporter = new DriverPDFExporter(driverReport);
        return exporter.export();
    }

    @Override
    public List<DriverReport> getReportForDriver(UUID driverId) {
        return driverReportRepository.findByDriverId(driverId);
    }

    @Override
    public ByteArrayInputStream downloadAdminReport() {
        List<AdminReport> adminReport = getReportForAdmin();
        AdminPDFExporter exporter = new AdminPDFExporter(adminReport);
        return exporter.export();
    }

    @Override
    public List<AdminReport> getReportForAdmin() {
        return adminReportRepository.findAll();
    }
}
