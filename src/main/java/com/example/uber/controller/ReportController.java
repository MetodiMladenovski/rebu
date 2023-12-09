package com.example.uber.controller;

import com.example.uber.model.immutable.AdminReport;
import com.example.uber.model.immutable.DriverReport;
import com.example.uber.model.immutable.PassengerReport;
import com.example.uber.service.ReportService;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<List<PassengerReport>> getReportForPassenger(@PathVariable UUID passengerId){
        List<PassengerReport> passengerReport = reportService.getReportForPassenger(passengerId);
        return ResponseEntity.ok(passengerReport);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<AdminReport>> getReportForAdmin(){
        List<AdminReport> passengerReport = reportService.getReportForAdmin();
        return ResponseEntity.ok(passengerReport);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<DriverReport>> getReportForDriver(@PathVariable UUID driverId){
        List<DriverReport> driverReport = reportService.getReportForDriver(driverId);
        return ResponseEntity.ok(driverReport);
    }

    @GetMapping(path = "/download/passenger/{passengerId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadPassengerReport(@PathVariable UUID passengerId) throws DocumentException, IOException {
        String headerValue = "inline; filename="+ passengerId.toString() + ".pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);
        ByteArrayInputStream bis = reportService.downloadPassengerReport(passengerId);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/download/driver/{driverId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadDriverReport(@PathVariable UUID driverId) throws DocumentException, IOException {
        String headerValue = "inline; filename="+ driverId.toString() + ".pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);
        ByteArrayInputStream bis = reportService.downloadDriverReport(driverId);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    @GetMapping(path = "/download/admin", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadAdminReport() throws DocumentException, IOException {
        String headerValue = "inline; filename=admin.pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);
        ByteArrayInputStream bis = reportService.downloadAdminReport();
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
