package org.example.norwegianid;

import java.text.ParseException;

public class TEST {
    public static void main(String[] args) throws ParseException {
        System.out.println("Examples with FNR and DNR:");
        System.out.println("**************************");
        System.out.println("Example FNR:");
        ExampleFNR.main(null);
        System.out.println("**************************");
        System.out.println("Examples DNR:");
        ExampleDNR.main(null);
        System.out.println("**************************");
    }
}
