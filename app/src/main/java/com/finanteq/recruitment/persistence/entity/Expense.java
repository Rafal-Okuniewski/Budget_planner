package com.finanteq.recruitment.persistence.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "expense")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expense_id")
    private int expenseId;

    @ColumnInfo(name = "user_creator_id")
    private final int userCreatorId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "period")
    private String period;

    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "created_at")
    private Date createdAt;

    @Ignore
    public Expense(int userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public Expense(int expenseId, int userCreatorId, String name, String category, String period,
                   double amount, Date createdAt) {
        this(userCreatorId);
        this.expenseId = expenseId;
        this.name = name;
        this.category = category;
        this.period = period;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public int getUserCreatorId() {
        return userCreatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
