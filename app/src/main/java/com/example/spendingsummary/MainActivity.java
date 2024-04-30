package com.example.spendingsummary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.createNewButton) {
            Toast.makeText(this, R.string.errorText, Toast.LENGTH_SHORT).show();
        } else if (viewId == R.id.statisticButton) {

        } else if (viewId == R.id.exitButton) {
            finish();
        } else {
            Toast.makeText(this, R.string.errorText, Toast.LENGTH_SHORT).show();
        }
    }



}