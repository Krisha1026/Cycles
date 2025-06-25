package com.example.citycyclerentals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private TextView nameTextView, emailTextView, phoneTextView;
    private Button editProfileButton, paymentMethodsButton, logoutButton ,shareExperienceButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameTextView = view.findViewById(R.id.nameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        editProfileButton = view.findViewById(R.id.editProfileButton);
        paymentMethodsButton = view.findViewById(R.id.paymentMethodsButton);
        shareExperienceButton = view.findViewById(R.id.shareExperienceButton);
        logoutButton = view.findViewById(R.id.logoutButton);

        // Set sample user data
        nameTextView.setText("Krishanthini");
        emailTextView.setText("krishanthini@gmail.com");
        phoneTextView.setText("0723456789");

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        paymentMethodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PaymentMethodsActivity.class));
            }
        });

        shareExperienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RentalExperienceActivity.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}