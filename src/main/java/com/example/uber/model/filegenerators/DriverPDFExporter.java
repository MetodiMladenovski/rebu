package com.example.uber.model.filegenerators;

import com.example.uber.model.immutable.DriverReport;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class DriverPDFExporter {
    private final List<DriverReport> reportToFormTableFor;

    public DriverPDFExporter(List<DriverReport> reportToFormTableFor) {
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

        Paragraph p = new Paragraph("Driver Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
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
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Passenger Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Passenger Surname", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Km travelled with passenger", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Average grade received per drive", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Earnings per km", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Number of drives", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total earnings", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (DriverReport row : reportToFormTableFor) {
            table.addCell(row.getPassengerName());
            table.addCell(row.getPassengerSurname());
            table.addCell(String.valueOf(row.getKmTravelledWithPassenger()));
            table.addCell(String.valueOf(row.getAverageGradeReceivedPerDrive()));
            table.addCell(String.valueOf(row.getEarningsPerKm()));
            table.addCell(String.valueOf(row.getNumberOfDrives()));
            table.addCell(String.valueOf(row.getTotalEarnings()));
        }
    }
}
