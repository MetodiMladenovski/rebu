package com.example.uber.model.filegenerators;

import com.example.uber.model.immutable.PassengerReport;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PassengerPDFExporter {
    private final List<PassengerReport> reportToFormTableFor;

    public PassengerPDFExporter(List<PassengerReport> reportToFormTableFor) {
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

        Paragraph p = new Paragraph("Passenger Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
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
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Driver Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Driver Surname", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Km travelled with driver", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price per kilometer", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total price paid", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (PassengerReport row : reportToFormTableFor) {
            table.addCell(row.getDriverName());
            table.addCell(row.getDriverSurname());
            table.addCell(String.valueOf(row.getKmTravelledWithDriver()));
            table.addCell(String.valueOf(row.getPricePerKm()));
            table.addCell(String.valueOf(row.getTotalPricePaid()));
        }
    }
}
