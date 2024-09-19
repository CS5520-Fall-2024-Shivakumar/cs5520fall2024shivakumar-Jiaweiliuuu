package com.example.numadfa_jiaweiliu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonAboutMe;

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
    }
}
