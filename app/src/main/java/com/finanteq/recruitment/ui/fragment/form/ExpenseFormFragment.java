package com.finanteq.recruitment.ui.fragment.form;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.finanteq.recruitment.R;
import com.finanteq.recruitment.databinding.FragmentExpenseFormBinding;
import com.finanteq.recruitment.di.ViewModelProviderFactory;
import com.finanteq.recruitment.utils.DecimalValueFilter;
import com.finanteq.recruitment.utils.ValidationUtils;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.Date;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ExpenseFormFragment extends DaggerFragment implements View.OnClickListener {

    private FragmentExpenseFormBinding binding;

    private ExpenseFormViewModel viewModel;

    @Inject
    public ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExpenseFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, providerFactory).get(ExpenseFormViewModel.class);

        initAutoCompleteTextView(R.array.expensePeriod, binding.actvPeriod);
        initAutoCompleteTextView(R.array.expenseCategory, binding.actvCategory);
        initViewListeners();
        setDecimalInputFilters();
        disableAutoCompleteTextViewInput();
    }

    private void initViewListeners() {
        binding.actvCategory.setOnItemClickListener((parent, view, position, rowId) -> {
            final String category = (String) parent.getItemAtPosition(position);
            viewModel.getExpense().setCategory(category);
        });

        binding.actvPeriod.setOnItemClickListener((parent, view, position, rowId) -> {
            final String period = (String) parent.getItemAtPosition(position);
            viewModel.getExpense().setPeriod(period);
        });

        binding.etName.addTextChangedListener(ValidationUtils.createWatcher(binding.etlName));
        binding.etAmount.addTextChangedListener(ValidationUtils.createWatcher(binding.etlAmount));

        binding.actvPeriod.addTextChangedListener(ValidationUtils.createWatcher(binding.etlPeriod));
        binding.actvCategory.addTextChangedListener(ValidationUtils.createWatcher(binding.etlCategory));

        binding.btnExpenseAdd.setOnClickListener(this);
    }

    private void setDecimalInputFilters() {
        binding.etAmount.setFilters(new InputFilter[]{
                new DecimalValueFilter(2), new InputFilter.LengthFilter(10)
        });
    }

    private void disableAutoCompleteTextViewInput() {
        binding.actvPeriod.setKeyListener(null);
        binding.actvCategory.setKeyListener(null);
    }

    @Override
    public void onClick(@NonNull View view) {
        final int viewId = view.getId();
        if (viewId == binding.btnExpenseAdd.getId()) {
            validateFormAndAddIfValid();
        }
    }

    private void initAutoCompleteTextView(@ArrayRes int arrayRes, MaterialAutoCompleteTextView actv) {
        final String[] items = getResources().getStringArray(arrayRes);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_dropdown_item_1line, items);
        actv.setAdapter(adapter);
    }

    private void validateFormAndAddIfValid() {
        final String name = ValidationUtils.validateStringValue(requireContext(),
                binding.etName, binding.etlName);
        final Double amount = ValidationUtils.validateDoubleValue(requireContext(),
                binding.etAmount, binding.etlAmount);
        final String category = ValidationUtils.validateStringValue(requireContext(),
                binding.actvCategory, binding.etlCategory);
        final String period = ValidationUtils.validateStringValue(requireContext(),
                binding.actvPeriod, binding.etlPeriod);

        if (name == null) {
            return;
        }
        viewModel.getExpense().setName(name);

        if (amount == null) {
            return;
        }
        viewModel.getExpense().setAmount(amount);

        if (category == null || period == null) {
            return;
        }

        viewModel.getExpense().setCreatedAt(new Date());
        viewModel.addExpense();

        navigateToSummaryForm();
    }

    private void navigateToSummaryForm() {
        NavHostFragment.findNavController(ExpenseFormFragment.this)
                .navigate(R.id.action_FormFragment_to_SummaryForm);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}