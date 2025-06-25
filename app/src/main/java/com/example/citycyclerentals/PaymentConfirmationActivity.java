package com.example.citycyclerentals;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class PaymentConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        // Initialize views
        TextView confirmationText = findViewById(R.id.confirmationText);
        TextView bikeNameText = findViewById(R.id.confirmationBikeName);
        TextView amountText = findViewById(R.id.confirmationAmount);
        TextView paymentMethodText = findViewById(R.id.confirmationPaymentMethod);
        ImageView bikeImageView = findViewById(R.id.confirmationBikeImage);
        Button doneButton = findViewById(R.id.doneButton);

        // Get data from intent
        String bikeName = getIntent().getStringExtra("bike_name");
        String amount = getIntent().getStringExtra("amount");
        String paymentMethod = getIntent().getStringExtra("payment_method");

        // Set the data to views
        confirmationText.setText("Payment Successful!");
        bikeNameText.setText(bikeName);
        amountText.setText(amount);
        paymentMethodText.setText(paymentMethod);

        // Load bike image based on type
        int imageResId;
        if (bikeName.contains("Mountain")) {
            imageResId = R.drawable.bike_mountain;
        } else if (bikeName.contains("Electric")) {
            imageResId = R.drawable.bike_electric;
        } else {
            imageResId = R.drawable.bike_standard;
        }
        Picasso.get().load(imageResId).into(bikeImageView);

        // Handle Done button click
        doneButton.setOnClickListener(v -> {
            // Go back to main activity and clear the stack
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}