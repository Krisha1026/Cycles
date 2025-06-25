package com.example.citycyclerentals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;

public class PricingPromotionsFragment extends Fragment {

    public PricingPromotionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_pricing_promotions, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find all buttons in the layout
        Button payAsYouGoButton = findButtonByText(view, "SELECT PLAN", 0); // First SELECT PLAN button
        Button dailyPassButton = findButtonByText(view, "SELECT PLAN", 1); // Second SELECT PLAN button
        Button monthlyMembershipButton = findButtonByText(view, "SELECT PLAN", 2); // Third SELECT PLAN button
        Button contactSupportButton = findButtonByText(view, "CONTACT SUPPORT", 0); // Contact support button

        // Set click listeners
        if (payAsYouGoButton != null) {
            payAsYouGoButton.setOnClickListener(v -> showPlanSelected("Pay-as-You-Go"));
        }

        if (dailyPassButton != null) {
            dailyPassButton.setOnClickListener(v -> showPlanSelected("Daily Pass"));
        }

        if (monthlyMembershipButton != null) {
            monthlyMembershipButton.setOnClickListener(v -> showPlanSelected("Monthly Membership"));
        }

        if (contactSupportButton != null) {
            contactSupportButton.setOnClickListener(v -> showSupportMessage());
        }
    }


    private Button findButtonByText(View parentView, String buttonText, int index) {
        int foundCount = 0;
        // Search through all buttons in the view
        for (int i = 0; i < ((ViewGroup) parentView).getChildCount(); i++) {
            View child = ((ViewGroup) parentView).getChildAt(i);
            if (child instanceof ViewGroup) {
                Button foundButton = findButtonInViewGroup((ViewGroup) child, buttonText, index, new int[]{0});
                if (foundButton != null) {
                    return foundButton;
                }
            }
        }
        return null;
    }

    // Recursive helper to search through ViewGroups
    private Button findButtonInViewGroup(ViewGroup viewGroup, String buttonText, int targetIndex, int[] currentIndex) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                if (button.getText().toString().equals(buttonText)) {
                    if (currentIndex[0] == targetIndex) {
                        return button;
                    }
                    currentIndex[0]++;
                }
            } else if (child instanceof ViewGroup) {
                Button foundButton = findButtonInViewGroup((ViewGroup) child, buttonText, targetIndex, currentIndex);
                if (foundButton != null) {
                    return foundButton;
                }
            }
        }
        return null;
    }

    private void showPlanSelected(String planName) {
        // Show a Snackbar message at the bottom of the screen
        if (getView() != null) {
            Snackbar.make(getView(), planName + " selected successfully!", Snackbar.LENGTH_LONG)
                    .setAction("OK", v -> { /* Dismiss action */ })
                    .setBackgroundTint(0xFF6200EE)
                    .setTextColor(0xFFFFFFFF)
                    .setActionTextColor(0xFFFFFFFF)
                    .show();
        }


    }

    private void showSupportMessage() {
        // Show a Toast message
        Toast.makeText(getContext(), "Opening support options...", Toast.LENGTH_SHORT).show();


    }


}