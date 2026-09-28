package com.skybook.backend.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.skybook.backend.entity.Booking;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generateTicket(Booking booking) throws Exception {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);

        document.add(new Paragraph("SkyBook E-Ticket", title));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Passenger : " + booking.getPassengerName()));
        document.add(new Paragraph("PNR : " + booking.getPnr()));
        document.add(new Paragraph("Flight : " + booking.getFlight().getFlightNumber()));
        document.add(new Paragraph("Route : " +
                booking.getFlight().getSource() + " -> " +
                booking.getFlight().getDestination()));
        document.add(new Paragraph("Seat : " + booking.getSeatNumber()));
        document.add(new Paragraph("Status : " + booking.getBookingStatus()));

        document.close();
        return out.toByteArray();
    }
}
