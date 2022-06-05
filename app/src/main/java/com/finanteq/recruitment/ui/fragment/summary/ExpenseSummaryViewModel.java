package com.finanteq.recruitment.ui.fragment.summary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.finanteq.recruitment.persistence.entity.Expense;
import com.finanteq.recruitment.persistence.entity.UserWithExpenses;
import com.finanteq.recruitment.repositories.ExpenseRepository;

import javax.inject.Inject;

public class ExpenseSummaryViewModel extends ViewModel {

    private final ExpenseRepository expenseRepository;

    @Inject
    public ExpenseSummaryViewModel(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public LiveData<UserWithExpenses> getUserWithExpenses() {
        return expenseRepository.getUsersWithExpenses();
    }

    public void deleteExpense(Expense expense) {
        expenseRepository.deleteExpense(expense);
    }

}
