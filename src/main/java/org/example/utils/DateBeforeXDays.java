package org.example.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class DateBeforeXDays {
    public static void main(String[] args) {
        int daysBefore = 90;
        LocalDateTime now = LocalDateTime.now();
        System.out.println("________Now: "+now);
        System.out.println(daysBefore + " days ago: "+ now.minusDays(daysBefore));

        //Running date: 2021-01-04T15:48:12.755, validThroughDate: 2020-10-05T23:59:59, period in days: -90

        String val1 = "2021-01-04T15:48:59";
        //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        LocalDateTime d1 = LocalDateTime.parse(val1,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("Specific date d1: "+d1);

        String val2 = "2020-10-05T23:00:01";
        LocalDateTime d2 = LocalDateTime.parse(val2);
        System.out.println("Specific date d2: "+d2);

        final Duration duration = Duration.between(d1, d2);
        long seconds = duration.getSeconds();
        long days = duration.toDays();

        System.out.print("Duration between d1 and d2: "+duration);
        System.out.print("-> days: "+days);
        System.out.println("-> seconds: "+seconds);

        //Custom datetime formatter
        LocalDateTime dx = LocalDateTime.parse("2021-01-04 08:48:59",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Custom datetime formmatter: " + dx);
    }
}
