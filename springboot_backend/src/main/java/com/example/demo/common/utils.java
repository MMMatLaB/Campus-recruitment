package com.example.demo.common;

import java.text.SimpleDateFormat;

public class utils {

    public static String time(){
        long date2 = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentTime2 = dateFormat.format(date2);
        /*System.out.println(currentTime2);*/
        return  currentTime2;
    }






}
