package com.example.spendingsummary.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.spendingsummary.R;
import com.example.spendingsummary.data.model.OperationWindow;

import java.util.ArrayList;
import java.util.List;

public class AddStatisticAttributes extends AppCompatActivity {
    private List<String> incomeItemList = new ArrayList<>();
    private List<String> expenseItemList = new ArrayList<>();
    private ArrayAdapter<String> adapterForIncome;
    private ArrayAdapter<String> adapterForExpense;
    private ListView listView;
    private boolean isKeyboardOpen;
    private boolean isIncome = true;
    private ToggleButton incomeExpensetoggleButton;
    private TextView incomeExpenseFieldText;

    @SuppressLint({"ClickableViewAccessibility", "ResourceAsColor"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_statistic_attributes);

        OperationWindow operationWindow = (OperationWindow) getIntent().getSerializableExtra("operationWindows");


        incomeExpensetoggleButton = findViewById(R.id.incomeExpensetoggleButton);
        incomeExpenseFieldText = findViewById(R.id.enterIncomeExpenseFieldText);


        incomeExpensetoggleButton.setBackgroundColor(ContextCompat.getColor(this, R.color.incomeColor));


        incomeExpenseFieldText.setHint(getString(R.string.enterText) + " " + getString(R.string.incomeText));


        if (operationWindow != null) {
            TextView textView = findViewById(R.id.statisticFieldTextID);
            textView.setText(operationWindow.getWindowName());
        }

        View rootView = findViewById(android.R.id.content);

        // Назначить слушатель касания
        rootView.setOnTouchListener((v, event) -> {
            hideKeyboard();
            return false;
        });

        adapterForIncome = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, incomeItemList);
        adapterForExpense = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseItemList);

        listView = findViewById(R.id.incomeList);
        listView.setAdapter(adapterForIncome);

        ListView incomListView = findViewById(R.id.incomeList);
        ListView expenseListView = findViewById(R.id.expenseList);

        // Устанавливаем одинаковый обработчик для обоих ListView
        setListViewClickListener(incomListView, adapterForIncome);
        setListViewClickListener(expenseListView, adapterForExpense);

        incomeExpenseFieldText.setOnTouchListener((v, event) -> {
            isKeyboardOpen = true;
            return false;
        });
    }

    private void setListViewClickListener(ListView listView, ArrayAdapter<String> adapter) {
        listView.setOnItemClickListener((parent, view, position, id) -> {

            if (isKeyboardOpen) {
                hideKeyboard();

            } else {
                // Удаляем элемент из адаптера по позиции
                adapter.remove(adapter.getItem(position));

                // Уведомляем адаптер об изменениях
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            isKeyboardOpen = false;
        }

    }

    public void onAddIncomeButtonClick(View view) {

        String text = incomeExpenseFieldText.getText().toString();

        if (!TextUtils.isEmpty(text)) {
            if (isIncome) {
                addItemToList(text, true);
            } else {
                addItemToList(text, false);
            }
            incomeExpenseFieldText.setText("");
        } else {
            Toast.makeText(this, "Текст отсутствует", Toast.LENGTH_SHORT).show();
        }
    }

    public void onToggleButtonClick(View view) {

        if (incomeExpensetoggleButton.isChecked()) {
            incomeExpensetoggleButton.setBackgroundColor(ContextCompat.getColor(this, R.color.expenseColor));
            incomeExpenseFieldText.setHint(getString(R.string.enterText) + " " + getString(R.string.expenseText));
            isIncome = false;
        } else {
            incomeExpensetoggleButton.setBackgroundColor(ContextCompat.getColor(this, R.color.incomeColor));
            incomeExpenseFieldText.setHint(getString(R.string.enterText) + " " + getString(R.string.incomeText));
            isIncome = true;
        }

        if (isIncome) {
            listView = findViewById(R.id.incomeList);
            listView.setAdapter(adapterForIncome);
        } else {
            listView = findViewById(R.id.expenseList);
            listView.setAdapter(adapterForExpense);
        }

    }


    private void addItemToList(String item, boolean isIncome) {
        if (isIncome) {
            incomeItemList.add(item);
            adapterForIncome.notifyDataSetChanged();
        } else {
            expenseItemList.add(item);
            adapterForExpense.notifyDataSetChanged();
        }
    }

    public void onFinishButtonOnClick(View view){


    }

}

