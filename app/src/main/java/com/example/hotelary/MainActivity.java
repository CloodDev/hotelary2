package com.example.hotelary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;


public class MainActivity extends AppCompatActivity {

    private int currentImageIndex = 0;
    private int[] imageResources = {
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Assume you have a Button and EditText in your activity_main.xml
        Button submitButton = findViewById(R.id.btnSubmit);

        // New elements from activity_main.xml
        final EditText dateFromField = findViewById(R.id.dateFrom);
        final EditText dateToField = findViewById(R.id.dateTo);
        final EditText adultsCountField = findViewById(R.id.adultsCount);
        final EditText kidsCountField = findViewById(R.id.kidsCount);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateFrom = dateFromField.getText().toString();
                String dateTo = dateToField.getText().toString();
                String adultsCount = adultsCountField.getText().toString();
                String kidsCount = kidsCountField.getText().toString();
                
                String formattedData = String.format(
                    "From: %s\nTo: %s\nAdults: %s\nKids: %s",
                    dateFrom, dateTo, adultsCount, kidsCount
                );
                
                showAlert(formattedData);
            }
        });

        // Image cycling logic
        final ImageView imageView = findViewById(R.id.imageCity);
        Button prevButton = findViewById(R.id.btnPrevImage);
        Button nextButton = findViewById(R.id.btnNextImage);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex - 1 + imageResources.length) % imageResources.length;
                imageView.setImageResource(imageResources[currentImageIndex]);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex + 1) % imageResources.length;
                imageView.setImageResource(imageResources[currentImageIndex]);
            }
        });
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Form Data")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show();
    }
}