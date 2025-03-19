package com.example.hotelary;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int currentImageIndex = 0;
    private int[][] imageResources = {
        {R.drawable.barcelona1, R.drawable.barcelona2, R.drawable.barcelona3, R.drawable.image1},
        {R.drawable.londyn1, R.drawable.londyn2, R.drawable.londyn3, R.drawable.image2},
        {R.drawable.paris1, R.drawable.paris2, R.drawable.paris3, R.drawable.image3}
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
        final ImageView hotelImage1 = findViewById(R.id.hotelImage1);
        final ImageView hotelImage2 = findViewById(R.id.hotelImage2);
        final ImageView hotelImage3 = findViewById(R.id.hotelImage3);
        
        Button prevButton = findViewById(R.id.btnPrevImage);
        Button nextButton = findViewById(R.id.btnNextImage);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex - 1 + imageResources.length) % imageResources.length;
                updateImages(imageView, hotelImage1, hotelImage2, hotelImage3);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex + 1) % imageResources.length;
                updateImages(imageView, hotelImage1, hotelImage2, hotelImage3);
            }
        });

        // Initial update of images
        updateImages(imageView, hotelImage1, hotelImage2, hotelImage3);
    }

    private void updateImages(ImageView mainImage, ImageView hotelImage1, ImageView hotelImage2, ImageView hotelImage3) {
        mainImage.setImageResource(imageResources[currentImageIndex][3]);
        hotelImage1.setImageResource(imageResources[currentImageIndex][0]);
        hotelImage2.setImageResource(imageResources[currentImageIndex][1]);
        hotelImage3.setImageResource(imageResources[currentImageIndex][2]);
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Form Data")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show();
    }
    
    public void showDateFromPickerDialog(View view) {
        Calendar today = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            (datePicker, year, month, day) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                ((EditText) findViewById(R.id.dateFrom)).setText(sdf.format(calendar.getTime()));
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        );
        
        // Set minimum date to today
        datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis());
        
        datePickerDialog.show();
    }    
    public void showDateToPickerDialog(View view) {
        Calendar today = Calendar.getInstance();
        
        // Get the "From" date if available
        EditText dateFromField = findViewById(R.id.dateFrom);
        String dateFromText = dateFromField.getText().toString();
        long minDate = today.getTimeInMillis();
        
        // If From date is set, use it as the minimum date
        if (!dateFromText.isEmpty()) {
            try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Calendar fromDate = Calendar.getInstance();
            fromDate.setTime(sdf.parse(dateFromText));
            minDate = fromDate.getTimeInMillis();
            } catch (Exception e) {
            // If parsing fails, fall back to today's date
            }
        }
        
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            (datePicker, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            ((EditText) findViewById(R.id.dateTo)).setText(sdf.format(calendar.getTime()));
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        );
        
        // Set minimum date to the "From" date or today if not available
        datePickerDialog.getDatePicker().setMinDate(minDate);
        
        datePickerDialog.show();
    }
}