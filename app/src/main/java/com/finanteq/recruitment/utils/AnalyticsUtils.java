package com.finanteq.recruitment.utils;

import com.finanteq.recruitment.persistence.entity.Expense;
import com.finanteq.recruitment.persistence.entity.UserWithExpenses;

public class AnalyticsUtils {

    public static double calculateBudgetIndicator(UserWithExpenses userWithExpenses) {
        final double budget = userWithExpenses.user.getMonthlyIncome() - userWithExpenses.user.getMonthlySavings();
        final double expensesSum = userWithExpenses.expenses.stream().map(Expense::getAmount).mapToDouble(Double::doubleValue).sum();
        final double balance = budget - expensesSum;
        if (budget != 0) {
            return balance / budget;
        }
        return 0;
    }

}
