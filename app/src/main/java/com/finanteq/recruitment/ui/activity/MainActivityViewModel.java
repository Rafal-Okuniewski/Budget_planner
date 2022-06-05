package com.finanteq.recruitment.ui.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.finanteq.recruitment.persistence.entity.User;
import com.finanteq.recruitment.repositories.ExpenseRepository;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {

    private final ExpenseRepository expenseRepository;

    private final User user;

    @Inject
    public MainActivityViewModel(ExpenseRepository expenseRepository, User user) {
        this.expenseRepository = expenseRepository;
        this.user = user;
    }

    public LiveData<Boolean> isUserEntityEmpty() {
        return expenseRepository.isUserEntityEmpty();
    }

    public void addUser() {
        expenseRepository.addUser(user);
    }

    public User getUser() {
        return user;
    }

}
