package com.example.numadfa_jiaweiliu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonAboutMe;
    private Button buttonQuickCalc;  // Declare new button for Quick Calc
    private Button buttonContactsCollector;  // New button for Contacts Collector


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the About Me button
        buttonAboutMe = findViewById(R.id.buttonAboutMe);
        buttonAboutMe.setOnClickListener(v -> {
            // Open AboutMeActivity
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
        });

        // Initialize the Quick Calc button
        buttonQuickCalc = findViewById(R.id.buttonQuickCalc);
        buttonQuickCalc.setOnClickListener(v -> {
            // Open QuickCalcActivity
            Intent intent = new Intent(MainActivity.this, QuickCalcActivity.class);
            startActivity(intent);
        });

        // Initialize the Contacts Collector button
        buttonContactsCollector = findViewById(R.id.buttonContactsCollector);
        buttonContactsCollector.setOnClickListener(v -> {
            // Open ContactsActivity
            Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
            startActivity(intent);
        });
    }
}
