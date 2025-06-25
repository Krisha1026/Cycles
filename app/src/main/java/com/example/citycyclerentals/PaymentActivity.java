package com.example.citycyclerentals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    private static final double BASE_RATE = 1500.00; // Base rate in Rs. per day

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Initialize all views
        TextView bikeName = findViewById(R.id.paymentBikeName);
        TextView bikeType = findViewById(R.id.paymentBikeType);
        TextView duration = findViewById(R.id.paymentDuration);
        TextView totalAmount = findViewById(R.id.paymentTotalAmount);
        RadioGroup paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        LinearLayout cardDetailsLayout = findViewById(R.id.cardDetailsLayout);
        CheckBox termsCheckBox = findViewById(R.id.termsCheckBox);
        Button payNowButton = findViewById(R.id.payNowButton);
        EditText cardNumberEditText = findViewById(R.id.cardNumberEditText);
        EditText expiryDateEditText = findViewById(R.id.expiryDateEditText);
        EditText cvvEditText = findViewById(R.id.cvvEditText);

        // Get data from intent with default values
        Intent intent = getIntent();
        String bikeNameStr = intent.getStringExtra("bike_name") != null ?
                intent.getStringExtra("bike_name") : "Bike";
        String bikeTypeStr = intent.getStringExtra("bike_type") != null ?
                intent.getStringExtra("bike_type") : "Standard";
        String durationStr = intent.getStringExtra("duration") != null ?
                intent.getStringExtra("duration") : "1 Day";

        // Calculate total amount if not passed
        String amountStr;
        if (intent.getStringExtra("amount") != null) {
            amountStr = intent.getStringExtra("amount");
        } else {
            int days = extractDaysFromDuration(durationStr);
            double total = calculateTotalAmount(days, bikeTypeStr);
            amountStr = formatCurrency(total);
        }

        // Set the data to views
        bikeName.setText(bikeNameStr);
        bikeType.setText(bikeTypeStr);
        duration.setText(String.format("Duration: %s", durationStr));
        totalAmount.setText(String.format("Total: %s", amountStr));

        // Payment method selection listener
        paymentMethodGroup.setOnCheckedChangeListener((group, checkedId) -> {
            cardDetailsLayout.setVisibility(checkedId == R.id.creditCardOption ?
                    View.VISIBLE : View.GONE);
        });

        // Pay Now button click listener
        payNowButton.setOnClickListener(v -> {
            if (!termsCheckBox.isChecked()) {
                termsCheckBox.setError("Please accept terms and conditions");
                return;
            }

            int selectedId = paymentMethodGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton radioButton = findViewById(selectedId);
            String paymentMethod = radioButton.getText().toString();

            if (selectedId == R.id.creditCardOption &&
                    !validateCardDetails(cardNumberEditText, expiryDateEditText, cvvEditText)) {
                return;
            }

            processPayment(bikeNameStr, amountStr, paymentMethod);
        });
    }

    private int extractDaysFromDuration(String duration) {
        try {
            return Integer.parseInt(duration.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private double calculateTotalAmount(int days, String bikeType) {
        double rate = BASE_RATE;
        // Adjust rate based on bike type if needed
        if (bikeType.equalsIgnoreCase("Electric")) {
            rate *= 1.5;
        } else if (bikeType.equalsIgnoreCase("Mountain")) {
            rate *= 1.2;
        }
        return days * rate;
    }

    private String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "lk"));
        return format.format(amount);
    }

    private boolean validateCardDetails(EditText cardNumber, EditText expiryDate, EditText cvv) {
        if (cardNumber.getText().toString().trim().length() != 16) {
            cardNumber.setError("Enter valid 16-digit card number");
            return false;
        }

        if (!expiryDate.getText().toString().matches("(0[1-9]|1[0-2])/?[0-9]{2}")) {
            expiryDate.setError("Enter valid MM/YY format");
            return false;
        }

        if (cvv.getText().toString().trim().length() != 3) {
            cvv.setError("Enter valid 3-digit CVV");
            return false;
        }

        return true;
    }

    private void processPayment(String bikeName, String amount, String paymentMethod) {

        //  show confirmation and finish

        Intent confirmationIntent = new Intent(this, PaymentConfirmationActivity.class);
        confirmationIntent.putExtra("bike_name", bikeName);
        confirmationIntent.putExtra("amount", amount);
        confirmationIntent.putExtra("payment_method", paymentMethod);
        startActivity(confirmationIntent);
        finish();
    }
}