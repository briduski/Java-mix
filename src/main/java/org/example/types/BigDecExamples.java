package org.example.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecExamples {
    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble("50"));
        System.out.println(bigDecimal);//50.0
        bigDecimal = bigDecimal.setScale(2);
        System.out.println("Scale: "+bigDecimal.scale());
        System.out.println(bigDecimal);//50.00
        bigDecimal = bigDecimal.stripTrailingZeros();
        System.out.println(bigDecimal);//5E+1
        System.out.println("Scale: "+bigDecimal.scale());
        if (bigDecimal.scale()<0)
            bigDecimal= bigDecimal.setScale(0);
        System.out.println(bigDecimal);//50
        bigDecimal = BigDecimal.valueOf(Double.parseDouble("50.20"));
        bigDecimal = bigDecimal.setScale(2);
        bigDecimal = bigDecimal.stripTrailingZeros();
        if (bigDecimal.scale()<0)
            bigDecimal= bigDecimal.setScale(0);
        System.out.println(bigDecimal);//50.2
        bigDecimal = BigDecimal.valueOf(Double.parseDouble("50"));
        bigDecimal = bigDecimal.setScale(2);
        bigDecimal = bigDecimal.stripTrailingZeros();
        System.out.println(bigDecimal);//5E+1
        bigDecimal = BigDecimal.valueOf(Double.parseDouble("50.20"));
        bigDecimal = bigDecimal.setScale(2);
        bigDecimal = bigDecimal.stripTrailingZeros();
        System.out.println(bigDecimal);//50.2
        System.out.println("Scale: "+bigDecimal.scale());

        BigDecimal toPay = new BigDecimal(1453.00005);
        toPay = toPay.multiply(new BigDecimal(1)).setScale(2, RoundingMode.HALF_UP);
        System.out.println(toPay + ", scale: "+toPay.scale());
    }

}
