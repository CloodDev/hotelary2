package com.example.hotelary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Assume you have a Button and EditText in your activity_main.xml
        Button submitButton = findViewById(R.id.btnSubmit);
        final EditText inputField = findViewById(R.id.etInput);
        
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = inputField.getText().toString();
                showAlert(inputText);
            }
        });
    }
    
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input Data")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show();
    }
}


