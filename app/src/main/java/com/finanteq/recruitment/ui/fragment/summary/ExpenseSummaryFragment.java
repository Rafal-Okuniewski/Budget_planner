package com.finanteq.recruitment.ui.fragment.summary;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.finanteq.recruitment.R;
import com.finanteq.recruitment.ui.fragment.summary.adapter.ExpenseAdapter;
import com.finanteq.recruitment.databinding.FragmentExpenseSummaryBinding;
import com.finanteq.recruitment.di.ViewModelProviderFactory;
import com.finanteq.recruitment.persistence.entity.Expense;
import com.finanteq.recruitment.persistence.entity.UserWithExpenses;
import com.finanteq.recruitment.utils.AnalyticsUtils;
import com.finanteq.recruitment.utils.FormatUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ExpenseSummaryFragment extends DaggerFragment implements View.OnClickListener {

    private FragmentExpenseSummaryBinding binding;

    private ExpenseSummaryViewModel viewModel;

    private ExpenseAdapter expenseAdapter;

    @Inject
    public ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExpenseSummaryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, providerFactory).get(ExpenseSummaryViewModel.class);

        initExpensesRecyclerView();
        initCategoryChart();
        subscribeExpensesObserver();
        initViewListeners();
    }

    private void initViewListeners() {
        binding.btnShowExpenseForm.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View view) {
        final int viewId = view.getId();
        if (viewId == binding.btnShowExpenseForm.getId()) {
            navigateToFormFragment();
        }
    }

    private void navigateToFormFragment() {
        NavHostFragment.findNavController(ExpenseSummaryFragment.this)
                .navigate(R.id.action_SummaryFragment_to_FormFragment);
    }

    private void subscribeExpensesObserver() {
        viewModel.getUserWithExpenses().observe(getViewLifecycleOwner(), userWithExpenses -> {
            if (userWithExpenses != null) {
                updateSummaryCard(userWithExpenses);
                updateAdapterItems(userWithExpenses.expenses);
            }
        });
    }

    private void updateSummaryCard(UserWithExpenses userExpenses) {
        updateCategoryChart(userExpenses);
        updateSummaryText(userExpenses);
    }

    private void updateAdapterItems(List<Expense> expenses) {
        if (expenses != null) {
            expenseAdapter.setItems(expenses);
            updateEmptyText(expenses);
        }
    }

    private void updateEmptyText(@NonNull List<Expense> expenses) {
        binding.tvExpensesEmpty.setVisibility(expenses.size() > 0 ? View.GONE : View.VISIBLE);
    }

    private void initExpensesRecyclerView() {
        expenseAdapter = new ExpenseAdapter(position ->
                viewModel.deleteExpense(expenseAdapter.getSelected(position)));
        binding.rvExpenses.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));
        binding.rvExpenses.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvExpenses.setAdapter(expenseAdapter);
    }

    private void initCategoryChart() {
        binding.chartCategory.setUsePercentValues(true);
        binding.chartCategory.getDescription().setEnabled(false);
        binding.chartCategory.setDragDecelerationFrictionCoef(0.95f);
        binding.chartCategory.setDrawCenterText(true);
        binding.chartCategory.setDrawEntryLabels(false);

        binding.chartCategory.setHoleRadius(50f);
        binding.chartCategory.setTransparentCircleRadius(56f);

        binding.chartCategory.setRotationAngle(0);
        binding.chartCategory.setRotationEnabled(true);
        binding.chartCategory.setHighlightPerTapEnabled(true);

        binding.chartCategory.animateY(1400, Easing.EaseInOutQuad);

        binding.chartCategory.setEntryLabelColor(Color.WHITE);
        binding.chartCategory.setEntryLabelTextSize(12f);
        initCategoryChartLegend();
    }

    private void initCategoryChartLegend() {
        final Legend legend = binding.chartCategory.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
    }

    private ArrayList<PieEntry> createChartDataSet(UserWithExpenses userExpenses) {
        final Map<String, List<Double>> expensesByCategory = new HashMap<>();
        for (final Expense expense : userExpenses.expenses) {
            final List<Double> list = expensesByCategory.computeIfAbsent(
                    expense.getCategory(), k -> new ArrayList<>());
            list.add(expense.getAmount());
        }

        final ArrayList<PieEntry> entries = new ArrayList<>();
        expensesByCategory.forEach((key, values) -> entries.add(new PieEntry(
                (float) values.stream().mapToDouble(Double::doubleValue).sum(), key)));
        return entries;
    }

    private void updateCategoryChart(@NonNull UserWithExpenses userExpenses) {
        final ArrayList<PieEntry> entries = createChartDataSet(userExpenses);
        final PieDataSet pieDataSet = new PieDataSet(entries, null);
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        updateChartCenterText(entries);

        final PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);

        binding.chartCategory.setData(pieData);
        binding.chartCategory.highlightValues(null);
        binding.chartCategory.invalidate();
    }

    private void updateChartCenterText(ArrayList<PieEntry> pieEntries) {
        if (pieEntries.isEmpty()) {
            binding.chartCategory.setCenterText(getString(R.string.fragment_summary_chart_no_data));
        } else {
            binding.chartCategory.setCenterText(getString(R.string.fragment_summary_chart_category));
        }
    }

    private void updateSummaryText(UserWithExpenses userExpenses) {
        final double indicator = AnalyticsUtils.calculateBudgetIndicator(userExpenses);
        if (indicator < 0) {
            binding.tvSummaryInfo.setText(getString(R.string.fragment_summary_expense_tv_overrun_percent));
            binding.tvSummaryPercent.setTextColor(ContextCompat.getColor(requireContext(), R.color.crimson));
        } else {
            binding.tvSummaryInfo.setText(getString(R.string.fragment_summary_expense_tv_limit_percent));
            binding.tvSummaryPercent.setTextColor(ContextCompat.getColor(requireContext(), R.color.bright_green));
        }
        binding.tvSummaryPercent.setText(FormatUtils.getPercentageFormat(indicator));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}