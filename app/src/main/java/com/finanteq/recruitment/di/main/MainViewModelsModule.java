package com.finanteq.recruitment.di.main;

import androidx.lifecycle.ViewModel;

import com.finanteq.recruitment.ui.activity.MainActivityViewModel;
import com.finanteq.recruitment.di.ViewModelKey;
import com.finanteq.recruitment.ui.fragment.form.ExpenseFormViewModel;
import com.finanteq.recruitment.ui.fragment.summary.ExpenseSummaryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExpenseFormViewModel.class)
    public abstract ViewModel bindExpenseFormViewModel(ExpenseFormViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ExpenseSummaryViewModel.class)
    public abstract ViewModel bindExpenseSummaryViewModel(ExpenseSummaryViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel bindMainActivityViewModel(MainActivityViewModel viewModel);
}

