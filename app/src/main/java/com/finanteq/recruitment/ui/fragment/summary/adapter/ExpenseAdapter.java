package com.finanteq.recruitment.ui.fragment.summary.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finanteq.recruitment.databinding.ItemRvExpenseBinding;
import com.finanteq.recruitment.persistence.entity.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {

    private final OnExpenseListener onExpenseListener;

    private List<Expense> expenses;

    public ExpenseAdapter(OnExpenseListener onExpenseListener) {
        this.onExpenseListener = onExpenseListener;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ItemRvExpenseBinding binding = ItemRvExpenseBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ExpenseViewHolder(binding, onExpenseListener);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        final Expense item = expenses.get(position);
        holder.onBind(item);
    }

    @Override
    public int getItemCount() {
        if (expenses != null) {
            return expenses.size();
        }
        return 0;
    }

    public Expense getSelected(int position) {
        if (expenses != null && expenses.size() > 0) {
            return expenses.get(position);
        }
        return null;
    }

    public void setItems(List<Expense> list) {
        this.expenses = list;
        notifyDataSetChanged();
    }

}
