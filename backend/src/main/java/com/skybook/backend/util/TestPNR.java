package com.skybook.backend.util;

public class TestPNR {

    public static void main(String[] args){

        for(int i=1;i<=5;i++){
            System.out.println(PnrGenerator.generate());
        }
    }
}
