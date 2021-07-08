package com.project.Investment.App.util.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.lang.Math.abs;

public class BigFunctions {

    private static final MathContext mc = new MathContext(6);

    public static Double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Rounding a number is incorrect");

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static Double multiply(Double d1, Double d2) {

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);

        return b1.multiply(b2, mc).doubleValue();
    }

    public static Double divide(Double d1, Double d2) {

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);

        return b1.divide(b2, mc).doubleValue();
    }

    public static Double add(Double d1, Double d2) {

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);

        return b1.add(b2, mc).doubleValue();
    }

    public static Double subtract(Double d1, Double d2) {

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);

        return b1.subtract(b2, mc).doubleValue();
    }


    public static boolean equals(Double d1, Double d2) {
        return abs(d1 - d2) < 1E-7;
    }
}
