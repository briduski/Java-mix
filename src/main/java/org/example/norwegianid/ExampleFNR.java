package org.example.norwegianid;

import no.bekk.bekkopen.person.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
public class ExampleFNR {
    public static void main(String[] args) throws ParseException {
        ZoneId defaultZoneId = ZoneId.systemDefault();

//        LocalDate localDate = LocalDate.of(1990, 12, 24);
//        LocalDate localDate = LocalDate.of(1941, 11, 14); // ok
        LocalDate localDate = LocalDate.of(1991, 10, 13);
        System.out.println(localDate);
        Date exampleDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        Fodselsnummer exampleFnr =  FodselsnummerCalculator.getFodselsnummerForDate(exampleDate);
        System.out.println("Example created with date: "+ exampleFnr);

        final List<Fodselsnummer> exampleFnrWithGender = FodselsnummerCalculator.getFodselsnummerForDateAndGender(exampleDate, KJONN.KVINNE);
        System.out.println("Number fnr created with date + female gender: "+ exampleFnrWithGender.size());

        List<Fodselsnummer> listOfValidFnr=  FodselsnummerCalculator.getManyFodselsnummerForDate(exampleDate); 
        System.out.println("Number fnr created with date: "+ listOfValidFnr.size());


        // Validate input FNR
        String fnrAsString = exampleFnrWithGender.get(0).toString();

        Navn name1= new Navn("Name1", "Surname1");
        Fodselsnummer fnr1 =  FodselsnummerValidator.getFodselsnummer(fnrAsString);
        Person person1 = new Person(name1,fnr1 );
        System.out.println(" __Example FNR: "+ fnrAsString);
        System.out.println(" __Dateofbirth: "+ fnr1.getDateOfBirth());
        System.out.println(" getFodselsdatoAsString/ Date of birth: " + person1.getFodselsdatoAsString());
        System.out.println(" Person number: ______" + person1.getPersonnummer());
        System.out.println(" getFodselsdato/ Date of birth: " + person1.getFodselsdato());
        System.out.print(" I am woman:? " + person1.erKvinne());
        System.out.println(", I am man:? " + person1.erMann());
    }
}
