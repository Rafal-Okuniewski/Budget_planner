package com.finanteq.recruitment.di.main;

import com.finanteq.recruitment.persistence.entity.Expense;
import com.finanteq.recruitment.persistence.entity.User;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    static Expense provideExpense(int userId) {
        return new Expense(userId);
    }

    @Provides
    static User provideUser(int userId) {
        return new User(userId);
    }

}
