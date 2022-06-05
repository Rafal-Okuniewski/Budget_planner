package com.finanteq.recruitment.ui.fragment.form;

import androidx.lifecycle.ViewModel;

import com.finanteq.recruitment.persistence.entity.Expense;
import com.finanteq.recruitment.repositories.ExpenseRepository;

import javax.inject.Inject;

public class ExpenseFormViewModel extends ViewModel {

    private final ExpenseRepository expenseRepository;

    private final Expense expense;

    @Inject
    public ExpenseFormViewModel(ExpenseRepository expenseRepository, Expense expense) {
        this.expenseRepository = expenseRepository;
        this.expense = expense;
    }

    public void addExpense() {
        expenseRepository.addExpense(expense);
    }

    public Expense getExpense() {
        return expense;
    }

}
