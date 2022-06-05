package com.finanteq.recruitment.utils;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final String DATE_DD_MM_HH_MM = "dd.MM' 'HH:mm";

    private DateUtils() {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    @Nullable
    public static String getStringDate(Date date, String pattern) {
        final DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return date == null ? null : dateFormat.format(date);
    }

}
