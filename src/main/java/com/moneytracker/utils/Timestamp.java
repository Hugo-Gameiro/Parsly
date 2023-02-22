package com.moneytracker.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timestamp {

    public static String getTimeStamp() {

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(dateFormatter);

        LocalTime localTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        String time = localTime.format(timeFormatter);

        return date + "_" + time.replace(":", "-").replace(".", "_");
    }
}
