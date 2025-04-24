package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDates {

    public static void main(String[] args) {

        // current date and time
        LocalDate today = LocalDate.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        // reformats date into two-digit month and day and four-digit year
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(today.format(formatter1));

        // displays as the default
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(today.format(formatter5));

        // reformats into full month single digit day and four digit year
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        System.out.println(today.format(formatter2));

        // changing time zone to GMT which is 5 hours ahead
        ZonedDateTime gmtTime = ZonedDateTime.now(ZoneId.of("GMT"));
        // reformats into full day, shortened month, single digit day, four digit year and hour and minute
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy h:mm");
        System.out.println(gmtTime.format(formatter3));

        // uses single quotes to include on
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("h:mm 'on' dd-MMM-yyyy");
        System.out.println(currentDateTime.format(formatter4));

    }

}
