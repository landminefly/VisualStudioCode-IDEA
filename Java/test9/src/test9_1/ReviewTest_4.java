package test9_1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReviewTest_4
{
    public static void main(String[] args)
    {
        double d1 = 3.14;
        double d2 = 3.15;
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        System.out.println(b1.setScale(1, RoundingMode.HALF_UP)); //3.1
        System.out.println(b2.setScale(1, RoundingMode.HALF_UP)); //3.2
    }
}
