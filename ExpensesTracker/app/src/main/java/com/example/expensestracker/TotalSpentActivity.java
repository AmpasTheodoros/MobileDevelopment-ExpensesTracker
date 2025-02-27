package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TotalSpentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_spent_activity);

        TextView title = findViewById(R.id.title_of_top_bar); // gets TextView (top bar title)
        title.setText(R.string.money_spent_each_category_title); // sets title for top bar

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    @SuppressLint("NonConstantResourceId")
    public void menuChange (View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.categories_button:
                intent = new Intent(v.getContext(), TransactionCategoriesActivity.class);
                startActivity(intent);
                break;

            case R.id.money_spent_button:
                intent = new Intent(v.getContext(), TotalSpentActivity.class);
                startActivity(intent);
                break;

            case R.id.graph_button:
                //
                break;
        }

    }
}