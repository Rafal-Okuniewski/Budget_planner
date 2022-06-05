package com.finanteq.recruitment.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.finanteq.recruitment.R;
import com.finanteq.recruitment.databinding.FragmentDialogUserDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ValidationUtils {

    public static String validateStringValue(Context context, AutoCompleteTextView autoText,
                                             TextInputLayout layout) {
        final Editable editable = autoText.getText();
        return validateStringValue(context, editable, layout);
    }

    public static String validateStringValue(Context context, TextInputEditText text,
                                             TextInputLayout layout) {
        final Editable editable = text.getText();
        return validateStringValue(context, editable, layout);
    }

    public static String validateStringValue(Context context, Editable editable,
                                             TextInputLayout layout) {
        if (editable == null) {
            layout.setError(context.getString(R.string.validation_empty_value));
            return null;
        }

        final String userName = editable.toString().trim();
        if (editable.toString().trim().length() <= 0) {
            layout.setError(context.getString(R.string.validation_empty_value));
            return null;
        }

        return userName;
    }

    public static Double validateDoubleValue(Context context, TextInputEditText text,
                                             TextInputLayout layout) {
        final String value = validateStringValue(context, text, layout);
        if (value == null) {
            return null;
        }

        final Double doubleValue;

        try {
            doubleValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            layout.setError(context.getString(R.string.validation_incorrect_value));
            return null;
        }

        if (doubleValue < 0) {
            layout.setError(context.getString(R.string.validation_incorrect_negative_value));
            return null;
        }

        return doubleValue;
    }

    public static boolean validateGreaterThan(Context context, FragmentDialogUserDataBinding binding,
                                              Double income, Double savings) {
        if (income >= savings) {
            return true;
        }

        binding.etlIncome.setError(context.getString(R.string.validation_greater_than));
        binding.etlSavings.setError(context.getString(R.string.validation_greater_than));
        return false;
    }

    public static TextWatcher createWatcher(TextInputLayout inputLayout) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing to do
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //nothing to do
            }
        };
    }

}
