package com.finanteq.recruitment.persistence.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private final int userId;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "monthly_income")
    private double monthlyIncome;

    @ColumnInfo(name = "monthly_savings")
    private double monthlySavings;

    @Ignore
    public User(int userId) {
        this.userId = userId;
    }

    public User(int userId, String firstName, double monthlyIncome, double monthlySavings) {
        this(userId);
        this.firstName = firstName;
        this.monthlyIncome = monthlyIncome;
        this.monthlySavings = monthlySavings;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getMonthlySavings() {
        return monthlySavings;
    }

    public void setMonthlySavings(double monthlySavings) {
        this.monthlySavings = monthlySavings;
    }

}
