package com.finanteq.recruitment.utils;

import java.text.DecimalFormat;

public class FormatUtils {

    public static String getCurrencyFormat(double currency) {
        final DecimalFormat decimalFormat = new DecimalFormat("-#.00 PLN");
        return decimalFormat.format(currency);
    }

    public static String getPercentageFormat(double percentage) {
        final DecimalFormat decimalFormat = new DecimalFormat("#.00 %");
        return decimalFormat.format(percentage);
    }

}
