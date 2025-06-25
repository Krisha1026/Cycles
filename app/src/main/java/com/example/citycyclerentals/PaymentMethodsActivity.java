
package com.example.citycyclerentals;
import com.example.citycyclerentals.PaymentMethod;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodsActivity extends AppCompatActivity {
    private RecyclerView paymentMethodsRecyclerView;
    private TextView noPaymentMethodsTextView;
    private MaterialButton addPaymentMethodButton;
    private PaymentMethodsAdapter adapter;
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        // Initialize views
        paymentMethodsRecyclerView = findViewById(R.id.paymentMethodsRecyclerView);
        noPaymentMethodsTextView = findViewById(R.id.noPaymentMethodsTextView);
        addPaymentMethodButton = findViewById(R.id.addPaymentMethodButton);

        // Setup RecyclerView
        adapter = new PaymentMethodsAdapter(paymentMethods);
        paymentMethodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentMethodsRecyclerView.setAdapter(adapter);

        // Load sample data
        loadPaymentMethods();

        addPaymentMethodButton.setOnClickListener(v -> {
            // Open "Add Payment Method" screen

        });
    }

    private void loadPaymentMethods() {
        paymentMethods.add(new PaymentMethod("Visa •••• 4242", "Expires 12/25"));
        paymentMethods.add(new PaymentMethod("PayPal", "user@example.com"));
        adapter.notifyDataSetChanged();

        // Show empty state if no payment methods
        noPaymentMethodsTextView.setVisibility(
                paymentMethods.isEmpty() ? View.VISIBLE : View.GONE
        );
    }
}