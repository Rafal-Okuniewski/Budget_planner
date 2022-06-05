package com.finanteq.recruitment.persistence.entity;

import androidx.room.DatabaseView;
import androidx.room.Embedded;

@DatabaseView(viewName = "monthly_expense", value = "SELECT * FROM expense " +
        "WHERE date(created_at /1000, 'unixepoch') BETWEEN date('now','start of month') AND " +
        "date('now','start of month','+1 month','-1 day') ORDER BY created_at DESC")
public class MonthlyExpenseView {

    @Embedded
    public Expense expense;

}
