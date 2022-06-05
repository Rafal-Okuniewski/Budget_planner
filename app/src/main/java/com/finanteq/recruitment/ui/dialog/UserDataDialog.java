package com.finanteq.recruitment.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.finanteq.recruitment.R;
import com.finanteq.recruitment.databinding.FragmentDialogUserDataBinding;
import com.finanteq.recruitment.utils.DecimalValueFilter;
import com.finanteq.recruitment.utils.ValidationUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import timber.log.Timber;

public class UserDataDialog extends DialogFragment {

    private FragmentDialogUserDataBinding binding;

    private OnUserDialogListener listener;

    private Button positiveButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = FragmentDialogUserDataBinding.inflate(getLayoutInflater());
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(requireActivity())
                .setView(binding.getRoot())
                .setCancelable(false)
                .setIcon(R.drawable.ic_baseline_description_24)
                .setTitle(R.string.fragment_user_dialog_title)
                .setNegativeButton(R.string.fragment_user_dialog_btn_negative, (dialog, which) -> listener.dismissDialog())
                .setPositiveButton(R.string.fragment_user_dialog_btn_positive, (dialog, which) -> {})
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        initViewListeners();
        setDecimalInputFilters();
        return alertDialog;
    }

    private void initViewListeners() {
        binding.etUserName.addTextChangedListener(ValidationUtils.createWatcher(binding.etlUserName));
        binding.etIncome.addTextChangedListener(ValidationUtils.createWatcher(binding.etlIncome));
        binding.etSavings.addTextChangedListener(ValidationUtils.createWatcher(binding.etlSavings));
    }

    private void setDecimalInputFilters() {
        final InputFilter[] filters = new InputFilter[]{
                new DecimalValueFilter(2), new InputFilter.LengthFilter(10)
        };
        binding.etIncome.setFilters(filters);
        binding.etSavings.setFilters(filters);
    }

    private void validateFormAndAddIfValid() {
        final String userName = ValidationUtils.validateStringValue(requireContext(),
                binding.etUserName, binding.etlUserName);
        final Double income = ValidationUtils.validateDoubleValue(requireContext(),
                binding.etIncome, binding.etlIncome);
        final Double savings = ValidationUtils.validateDoubleValue(requireContext(),
                binding.etSavings, binding.etlSavings);

        if (userName == null || income == null || savings == null) {
            return;
        }

        if (ValidationUtils.validateGreaterThan(requireContext(), binding, income, savings)) {
            listener.sendInput(userName, income, savings);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null) {
            positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        }
        positiveButton.setOnClickListener(view -> validateFormAndAddIfValid());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OnUserDialogListener) context;
        } catch (ClassCastException e) {
            Timber.e(e, "onAttach: ClassCastException: %s", e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}