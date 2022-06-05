package com.finanteq.recruitment.ui.dialog;

public interface OnUserDialogListener {

    void dismissDialog();

    void sendInput(String name, double monthlyIncome, double monthlySaving);

}
