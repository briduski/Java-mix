package org.example.nitobook.problem2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;


abstract class  Calc  {

    private static final int N  = 100;
    public static final Random rand = new Random();

    public static final MathContext context =  new MathContext(N, RoundingMode.HALF_UP);
    public static BigDecimal random()   {
        StringBuilder   builder   = new  StringBuilder();
        builder.append((char)('1' + rand.nextInt(9)));
        for  (int i = 0; i< N;++i)  builder.append((char)('0' + rand.nextInt(10)));
        builder.append('.');
        for  (int i = 0; i< N;++i)  builder.append((char)('0' + rand.nextInt(10)));
        return new BigDecimal(builder.toString());
    }
    public static   BigDecimal sqrt(BigDecimal x) {
          BigDecimal value = BigDecimal.ZERO;
           if (x.signum() != 0) {
              BigInteger   t = x.movePointRight(N << 1).toBigInteger();
              BigInteger   y =  t.shiftRight((t.bitLength() + 1)  >> 1);
                for (BigInteger z = y;;  z  = y) {
                    y = y.add(t.divide(y)).shiftRight(1);
                    if (y.compareTo(z) == 0) break;
                }
                    value =  new BigDecimal(y, N);
            }
           return value;
        }

}
