package com.skybook.backend.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.ByteArrayOutputStream;

public class QrGenerator {

    public static byte[] generate(String text){

        try{

            BitMatrix matrix = new MultiFormatWriter().encode(
                    text,
                    BarcodeFormat.QR_CODE,
                    180,
                    180
            );

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(
                    matrix,
                    "PNG",
                    out
            );

            return out.toByteArray();

        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

}
