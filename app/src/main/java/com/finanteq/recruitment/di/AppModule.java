package com.finanteq.recruitment.di;

import android.app.Application;

import com.finanteq.recruitment.persistence.AppDatabase;
import com.finanteq.recruitment.persistence.dao.ExpenseDao;
import com.finanteq.recruitment.persistence.dao.UserDao;
import com.finanteq.recruitment.repositories.ExpenseRepository;
import com.finanteq.recruitment.utils.Constants;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static AppDatabase provideAppDatabase(Application application) {
        return AppDatabase.getInstance(application);
    }

    @Provides
    static UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.getUserDao();
    }

    @Provides
    static ExpenseDao provideExpenseDao(AppDatabase appDatabase) {
        return appDatabase.getExpenseDao();
    }

    @Provides
    static int provideUserId() {
        return Constants.USER_ID;
    }

    @Provides
    static ExpenseRepository provideExpenseRepository(ExpenseDao expenseDao, UserDao userDao, int userId) {
        return new ExpenseRepository(expenseDao, userDao, userId);
    }

}

