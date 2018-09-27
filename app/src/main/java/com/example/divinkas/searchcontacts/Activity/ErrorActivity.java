package com.example.divinkas.searchcontacts.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.divinkas.searchcontacts.R;

public class ErrorActivity extends AppCompatActivity {

    private Button btnNewStartApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        btnNewStartApp = findViewById(R.id.btnStartNewChance);
        btnNewStartApp.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
