package com.finanteq.recruitment.ui.fragment.summary.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finanteq.recruitment.databinding.ItemRvExpenseBinding;
import com.finanteq.recruitment.persistence.entity.Expense;
import com.finanteq.recruitment.utils.DateUtils;
import com.finanteq.recruitment.utils.FormatUtils;

public class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ItemRvExpenseBinding binding;

    private final OnExpenseListener onExpenseListener;

    public ExpenseViewHolder(@NonNull ItemRvExpenseBinding itemView, OnExpenseListener onExpenseListener) {
        super(itemView.getRoot());
        this.binding = itemView;
        this.onExpenseListener = onExpenseListener;
    }

    @Override
    public void onClick(@NonNull View view) {
        if (view.getId() == binding.btnDelete.getId()) {
            onExpenseListener.onButtonClick(getAdapterPosition());
        }
    }

    public void onBind(@NonNull Expense item) {
        binding.tvName.setText(item.getName());
        binding.tvCategory.setText(item.getCategory());
        binding.tvPeriod.setText(item.getPeriod());
        binding.tvAmount.setText(FormatUtils.getCurrencyFormat(item.getAmount()));
        binding.tvDate.setText(DateUtils.getStringDate(item.getCreatedAt(), DateUtils.DATE_DD_MM_HH_MM));
        binding.btnDelete.setOnClickListener(this);
    }

}
