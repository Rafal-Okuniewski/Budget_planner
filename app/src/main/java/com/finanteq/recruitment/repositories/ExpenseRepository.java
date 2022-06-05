package com.finanteq.recruitment.repositories;

import androidx.lifecycle.LiveData;

import com.finanteq.recruitment.persistence.dao.ExpenseDao;
import com.finanteq.recruitment.persistence.dao.UserDao;
import com.finanteq.recruitment.persistence.entity.Expense;
import com.finanteq.recruitment.persistence.entity.User;
import com.finanteq.recruitment.persistence.entity.UserWithExpenses;

import javax.inject.Inject;

public class ExpenseRepository {

    private final ExpenseDao expenseDao;

    private final UserDao userDao;

    private final int userId;

    @Inject
    public ExpenseRepository(ExpenseDao expenseDao, UserDao userDao, int userId) {
        this.expenseDao = expenseDao;
        this.userDao = userDao;
        this.userId = userId;
    }

    public LiveData<UserWithExpenses> getUsersWithExpenses() {
        return userDao.selectUserWithExpenses(userId);
    }

    public LiveData<Boolean> isUserEntityEmpty() {
        return userDao.isEntityEmpty();
    }

    public void addExpense(Expense expense) {
        AppExecutors.getInstance().diskIO().execute(() -> expenseDao.insertEntity(expense));
    }

    public void deleteExpense(Expense expense) {
        AppExecutors.getInstance().diskIO().execute(() -> expenseDao.deleteEntity(expense));
    }

    public void addUser(User user) {
        AppExecutors.getInstance().diskIO().execute(() -> userDao.insertEntity(user));
    }

}
