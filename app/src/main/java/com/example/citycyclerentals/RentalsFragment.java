package com.example.citycyclerentals;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RentalsFragment extends Fragment {
    private RecyclerView rentalsRecyclerView;
    private RentalsAdapter rentalsAdapter;
    private List<Rental> rentalList;
    private DatabaseHelper databaseHelper;
    private TextView noRentalsTextView;
    private Button startRentalButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rentals, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        rentalList = new ArrayList<>();

        rentalsRecyclerView = view.findViewById(R.id.rentalsRecyclerView);
        rentalsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rentalsAdapter = new RentalsAdapter(getActivity(), rentalList);
        rentalsRecyclerView.setAdapter(rentalsAdapter);

        noRentalsTextView = view.findViewById(R.id.noRentalsTextView);
        startRentalButton = view.findViewById(R.id.startRentalButton);

        startRentalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AllStationsActivity.class));
            }
        });

        loadUserRentals();

        return view;
    }

    private void loadUserRentals() {

        //  fetch all rentals
        Cursor cursor = databaseHelper.getAllRentals();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("rental_id"));
                int userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
                int bikeId = cursor.getInt(cursor.getColumnIndexOrThrow("bike_id"));
                String startTime = cursor.getString(cursor.getColumnIndexOrThrow("start_time"));
                String endTime = cursor.getString(cursor.getColumnIndexOrThrow("end_time"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));

                Rental rental = new Rental(id, userId, bikeId, startTime, endTime, price, status);
                rentalList.add(rental);
            } while (cursor.moveToNext());
            cursor.close();
            rentalsAdapter.notifyDataSetChanged();

            if (rentalList.isEmpty()) {
                noRentalsTextView.setVisibility(View.VISIBLE);
                rentalsRecyclerView.setVisibility(View.GONE);
            } else {
                noRentalsTextView.setVisibility(View.GONE);
                rentalsRecyclerView.setVisibility(View.VISIBLE);
            }
        }
    }
}