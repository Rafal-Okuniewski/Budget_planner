package com.finanteq.recruitment.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;;

import com.finanteq.recruitment.persistence.converter.Converters;
import com.finanteq.recruitment.persistence.dao.ExpenseDao;
import com.finanteq.recruitment.persistence.dao.UserDao;
import com.finanteq.recruitment.persistence.entity.MonthlyExpenseView;
import com.finanteq.recruitment.persistence.entity.Expense;
import com.finanteq.recruitment.persistence.entity.User;

@Database(entities = {User.class, Expense.class}, views = {MonthlyExpenseView.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "application_db";

    private static AppDatabase instance;

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract UserDao getUserDao();

    public abstract ExpenseDao getExpenseDao();

}
