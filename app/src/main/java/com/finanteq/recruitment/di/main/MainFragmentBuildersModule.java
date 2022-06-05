package com.finanteq.recruitment.di.main;

import com.finanteq.recruitment.ui.fragment.form.ExpenseFormFragment;
import com.finanteq.recruitment.ui.fragment.summary.ExpenseSummaryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ExpenseFormFragment contributeExpenseFormFragment();

    @ContributesAndroidInjector
    abstract ExpenseSummaryFragment contributeExpenseSummaryFragment();
}
