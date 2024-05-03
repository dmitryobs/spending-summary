package com.example.spendingsummary.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spendingsummary.R;
import com.example.spendingsummary.data.model.OperationWindow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OperationWindow operationWindows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.createNewButton) {
            showInputDialog();

        } else if (viewId == R.id.statisticButton) {

        } else if (viewId == R.id.exitButton) {
            finish();
        } else {
            Toast.makeText(this, R.string.errorText, Toast.LENGTH_SHORT).show();
        }
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_input, null);
        final EditText editText = dialogView.findViewById(R.id.editText);
        builder.setView(dialogView)
                .setTitle(R.string.statisticNameText)
                .setPositiveButton(R.string.okButtonText, (dialog, which) -> {

                    if (!TextUtils.isEmpty(editText.getText().toString())) {
                        operationWindows = new OperationWindow(editText.getText().toString(), new Date());

                        Intent intent = new Intent(MainActivity.this, AddStatisticAttributes.class);

                        String inputData = editText.getText().toString();
                        intent.putExtra("operationWindows", operationWindows);

                        startActivity(intent);
                    } else {
                        Toast.makeText(this, R.string.errorText, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancelButtonText, (dialog, which) -> dialog.cancel());
        final AlertDialog alertDialog = builder.create();
        // show keyboard automatically
        alertDialog.setOnShowListener(dialog -> new Handler().postDelayed(() -> {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }, 100));

        alertDialog.show();
    }

    public OperationWindow getOperationWindow() {
        return operationWindows;
    }
}
