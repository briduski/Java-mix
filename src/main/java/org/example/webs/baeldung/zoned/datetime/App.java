package org.example.webs.baeldung.zoned.datetime;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.time.ZoneId.getAvailableZoneIds;

// https://www.baeldung.com/java-format-zoned-datetime-string
public class App {
    public static void main(String[] args) {

        //printZones();

        // hardcoded
        ZonedDateTime zonedDateTimeOf
                = ZonedDateTime.of(2020, 05, 31,
                11, 07, 0, 0, ZoneId.of("Europe/Oslo"));

        System.out.println(zonedDateTimeOf);

        // Now - 1
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("Europe/Oslo"));
        System.out.println(zonedDateTimeNow); //Europe/Oslo

        // Now -2
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Oslo"));

        System.out.println(zonedDateTime);

        // ZonedDateTime to String - 1
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        String formattedString = zonedDateTime.format(formatter);
        System.out.println(formattedString);

        // ZonedDateTime to String - 2
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        String formattedString2 = zonedDateTime.format(formatter2);
        System.out.println(formattedString2);

        //String to ZonedDateTime - 1
        ZonedDateTime zonedDateTime2 = ZonedDateTime.parse("2020-12-03T10:15:30+01:00");
        System.out.println(zonedDateTime2);

        //String to ZonedDateTime - 2  
        ZoneId timeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime3 = LocalDateTime.parse("2020-12-03T10:15:30",
                DateTimeFormatter.ISO_DATE_TIME).atZone(timeZone);
        System.out.println(zonedDateTime3);
        String isoZonedDTStr = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        String rfcDate = zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        System.out.println(isoZonedDTStr);
        System.out.println(rfcDate);
    }
    public static void printZones(){
        System.out.println("Zones .. ");
        getAvailableZoneIds().forEach(x-> System.out.println(x));
    }
}
