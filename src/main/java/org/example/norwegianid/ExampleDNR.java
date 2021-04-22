package org.example.norwegianid;

import no.bekk.bekkopen.person.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ExampleDNR {
    public static void main(String[] args) throws ParseException {
        ZoneId defaultZoneId = ZoneId.systemDefault();

//        LocalDate localDate = LocalDate.of(1990, 12, 24);
        LocalDate localDate = LocalDate.of(1938, 11, 14);
        System.out.println(localDate);
        Date exampleDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        final List<Fodselsnummer> exampleDNR= FodselsnummerCalculator.getManyDNumberFodselsnummerForDate(exampleDate);
        System.out.println("Number DNR created with date: "+ exampleDNR.size());


        // Validate input DNR
        String dnrAsString = exampleDNR.get(0).toString();

        Navn name1= new Navn("Name1", "Surname1");
        Fodselsnummer fnr1 =  FodselsnummerValidator.getFodselsnummer(dnrAsString);
        Person person1 = new Person(name1,fnr1 );
        System.out.println(" __Example DNR: "+ dnrAsString);
        System.out.println(" Date of birth: " + person1.getFodselsdatoAsString());
        System.out.println(" Person number: ______" + person1.getPersonnummer());
        System.out.println(" Date of birth: " + person1.getFodselsdato());
        System.out.print(" I am woman:? " + person1.erKvinne());
        System.out.println(", I am man:? " + person1.erMann());
    }
}
