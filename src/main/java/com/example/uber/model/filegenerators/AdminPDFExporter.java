package com.example.uber.model.filegenerators;

import com.example.uber.model.immutable.AdminReport;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdminPDFExporter {
    private final List<AdminReport> reportToFormTableFor;

    public AdminPDFExporter(List<AdminReport> reportToFormTableFor) {
        this.reportToFormTableFor = reportToFormTableFor;
    }

    public ByteArrayInputStream export() throws DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Admin Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(12);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3.0f, 5.0f, 5.0f, 3.0f, 3.0f, 2.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(1);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(9);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Driver Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Driver Surname", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Driver email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Car model", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Car make", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Grade", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Km travelled", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("# of passengers", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("# of different requests", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Avg money per drive", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("# of drives", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total money made", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (AdminReport row : reportToFormTableFor) {
            table.addCell(row.getDriverName());
            table.addCell(row.getDriverSurname());
            table.addCell(String.valueOf(row.getDriverEmail()));
            table.addCell(String.valueOf(row.getModel()));
            table.addCell(String.valueOf(row.getMake()));
            table.addCell(String.format("%.2f", row.getDriverGrade()));
            table.addCell(String.format("%.2f", row.getTotalKmTravelled()));
            table.addCell(String.valueOf(row.getNumberOfDifferentPassengers()));
            table.addCell(String.valueOf(row.getNumberOfDifferentRequests()));
            table.addCell(String.format("%.2f", row.getAverageMoneyPerRequest()));
            table.addCell(String.valueOf(row.getNumberOfDrives()));
            table.addCell(String.format("%.2f", row.getTotalMoneyMade()));
        }
    }
}
