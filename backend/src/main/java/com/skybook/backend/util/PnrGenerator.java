package com.skybook.backend.util;

import java.security.SecureRandom;

public class PnrGenerator {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generate(){

        StringBuilder pnr = new StringBuilder("SKB");

        for(int i=0;i<6;i++){
            pnr.append(CHARS.charAt(
                    random.nextInt(CHARS.length())
            ));
        }

        return pnr.toString();
    }
}
