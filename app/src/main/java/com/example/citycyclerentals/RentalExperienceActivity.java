package com.example.citycyclerentals;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RentalExperienceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_experience);

        // Set up back button in action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Share Experience");
        }

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        EditText feedbackEditText = findViewById(R.id.feedbackEditText);
        Button submitButton = findViewById(R.id.submitButton);
        Button uploadPhotoButton = findViewById(R.id.uploadPhotoButton);

        submitButton.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            String feedback = feedbackEditText.getText().toString().trim();

            if (feedback.isEmpty()) {
                Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
                return;
            }

            // Process the feedback
            Toast.makeText(this, "Thanks for your feedback! Rating: " + rating, Toast.LENGTH_LONG).show();
            finish(); // Close the activity after submission
        });

        uploadPhotoButton.setOnClickListener(v -> {
            // Implement photo upload functionality
            Toast.makeText(this, "Photo upload feature would be implemented here", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Close the activity when back button is pressed
        return true;
    }
}