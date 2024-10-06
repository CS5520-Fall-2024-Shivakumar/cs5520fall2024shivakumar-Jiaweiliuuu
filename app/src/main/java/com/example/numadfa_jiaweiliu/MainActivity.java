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
        buttonAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = getString(R.string.my_name);
                String email = getString(R.string.my_email);
                String message = getString(R.string.about_me_message, name, email);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
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
