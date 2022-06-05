package com.finanteq.recruitment.utils;

import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;

import java.util.Locale;

public class DecimalValueFilter extends DigitsKeyListener {

    private final int digits;

    public DecimalValueFilter(int digits) {
        super(Locale.getDefault(), false, true);
        this.digits = digits;
    }



    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        final CharSequence outValue = super.filter(source, start, end, dest, dstart, dend);

        if (outValue != null) {
            source = outValue;
            start = 0;
            end = outValue.length();
        }

        final int length = end - start;

        if (length == 0) {
            return source;
        }

        int dLength = dest.length();

        for (int i = 0; i < dstart; i++) {
            if (dest.charAt(i) == '.') {
                return (dLength - (i + 1) + length > digits) ? "" : new SpannableStringBuilder(source, start, end);
            }
        }

        for (int i = start; i < end; ++i) {
            if (source.charAt(i) == '.') {
                if ((dLength - dend) + (end - (i + 1)) > digits) {
                    return "";
                } else {
                    break;
                }
            }
        }
        return new SpannableStringBuilder(source, start, end);
    }
}
