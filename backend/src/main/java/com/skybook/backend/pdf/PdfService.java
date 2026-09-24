package com.skybook.backend.pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.skybook.backend.dto.TicketResponse;
import com.skybook.backend.qr.QrGenerator;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generate(TicketResponse ticket) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        // ===== Header =====
        doc.add(new Paragraph("SKYBOOK AIRLINES")
                .setBold()
                .setFontSize(22)
                .setTextAlignment(TextAlignment.CENTER));

        doc.add(new Paragraph("BOARDING PASS")
                .setTextAlignment(TextAlignment.CENTER));

        doc.add(new Paragraph(" "));

        // ===== Passenger Details =====
        doc.add(new Paragraph("Passenger : " + ticket.getPassengerName()));
        doc.add(new Paragraph("PNR : " + ticket.getPnr()));
        doc.add(new Paragraph("Flight : " + ticket.getFlightNumber()));
        doc.add(new Paragraph("Route : " + ticket.getRoute()));

        // ===== Boarding Details =====
        doc.add(new Paragraph("Gate : " + ticket.getGate()));
        doc.add(new Paragraph("Boarding : " + ticket.getBoardingTime()));
        doc.add(new Paragraph("Terminal : " + ticket.getTerminal()));
        doc.add(new Paragraph("Seat : " + ticket.getSeat()));

        doc.add(new Paragraph("Booking Status : " + ticket.getBookingStatus()));
        doc.add(new Paragraph("Payment Status : " + ticket.getPaymentStatus()));

        doc.add(new Paragraph(" "));

        // ===== QR Code =====
        byte[] qr = QrGenerator.generate(ticket.getPnr());

        Image qrImage = new Image(ImageDataFactory.create(qr));
        qrImage.setWidth(140);
        qrImage.setHeight(140);

        doc.add(qrImage);

        doc.add(new Paragraph("Scan QR at Boarding Gate")
                .setTextAlignment(TextAlignment.CENTER));

        doc.add(new Paragraph("--------------------------------"));

        doc.add(new Paragraph("Have a pleasant journey!")
                .setBold()
                .setTextAlignment(TextAlignment.CENTER));

        doc.close();

        return out.toByteArray();
    }
}
