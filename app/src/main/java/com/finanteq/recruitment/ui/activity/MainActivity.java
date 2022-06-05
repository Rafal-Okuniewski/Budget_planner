package com.finanteq.recruitment.ui.activity;

import android.os.Bundle;

import com.finanteq.recruitment.R;
import com.finanteq.recruitment.di.ViewModelProviderFactory;
import com.finanteq.recruitment.ui.dialog.OnUserDialogListener;
import com.finanteq.recruitment.ui.dialog.UserDataDialog;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.finanteq.recruitment.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements OnUserDialogListener {

    private static final String USER_DATA_DIALOG_TAG = "USER_DIALOG";

    private AppBarConfiguration appBarConfiguration;

    private MainActivityViewModel viewModel;

    @Inject
    public ViewModelProviderFactory providerFactory;

    private UserDataDialog userDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this, providerFactory).get(MainActivityViewModel.class);

        setSupportActionBar(binding.toolbar);
        initNavController();
        subscribeUserObserver();
    }

    private void initNavController() {
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    private void subscribeUserObserver() {
        viewModel.isUserEntityEmpty().observe(this, isEmpty -> {
            if (isEmpty) {
                showUserDataDialog();
            }
        });
    }

    private void showUserDataDialog() {
        userDialog = new UserDataDialog();
        userDialog.show(getSupportFragmentManager(), USER_DATA_DIALOG_TAG);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void dismissDialog() {
        userDialog.dismiss();
        finishAndRemoveTask();
    }

    @Override
    public void sendInput(String name, double monthlyIncome, double monthlySaving) {
        viewModel.getUser().setFirstName(name);
        viewModel.getUser().setMonthlyIncome(monthlyIncome);
        viewModel.getUser().setMonthlySavings(monthlySaving);
        viewModel.addUser();
        userDialog.dismiss();
    }

}