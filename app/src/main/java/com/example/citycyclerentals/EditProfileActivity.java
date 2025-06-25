package com.example.citycyclerentals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private ImageButton changePhotoButton;
    private EditText nameEditText, emailEditText, phoneEditText, passwordEditText;
    private Button saveButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        profileImageView = findViewById(R.id.profileImageView);
        changePhotoButton = findViewById(R.id.changePhotoButton);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        saveButton = findViewById(R.id.saveButton);

        databaseHelper = new DatabaseHelper(this);

        // Load current user data
        loadUserData();

        // Set profile picture
        Picasso.get().load(R.drawable.ic_profile_placeholder).into(profileImageView);

        changePhotoButton.setOnClickListener(v -> {
            // Implement photo change functionality
            Toast.makeText(this, "Change photo clicked", Toast.LENGTH_SHORT).show();
        });

        saveButton.setOnClickListener(v -> saveProfileChanges());
    }

    private void loadUserData() {
         //  sample data
        nameEditText.setText("Krisha");
        emailEditText.setText("krisha@example.com");
        phoneEditText.setText("0776655443");
        passwordEditText.setText("");
    }

    private void saveProfileChanges() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // show a success message
        boolean updateSuccess = true;

        if (updateSuccess) {
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            // Return to profile screen
            startActivity(new Intent(this, ProfileFragment.class));
            finish();
        } else {
            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }
}