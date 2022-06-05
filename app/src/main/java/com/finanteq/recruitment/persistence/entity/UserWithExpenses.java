package com.finanteq.recruitment.persistence.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithExpenses {

    @Embedded
    public User user;

    @Relation(parentColumn = "user_id", entityColumn = "user_creator_id", entity = MonthlyExpenseView.class)
    public List<Expense> expenses;

}
