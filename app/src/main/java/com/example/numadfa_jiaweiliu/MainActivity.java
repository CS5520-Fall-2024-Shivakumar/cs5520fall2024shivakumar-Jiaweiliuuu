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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the button
        buttonAboutMe = findViewById(R.id.buttonAboutMe);

        // Set a click listener
        // In MainActivity.java
        buttonAboutMe.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
        });

        // Initialize the Quick Calc button
        buttonQuickCalc = findViewById(R.id.buttonQuickCalc);

        // Set click listener for Quick Calc button
        buttonQuickCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start QuickCalcActivity
                Intent intent = new Intent(MainActivity.this, QuickCalcActivity.class);
                startActivity(intent);  // Start the new activity
            }
        });


    }
}
