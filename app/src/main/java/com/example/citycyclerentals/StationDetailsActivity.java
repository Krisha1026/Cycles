package com.example.citycyclerentals;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class StationDetailsActivity extends AppCompatActivity {
    private TextView stationNameTextView, stationAddressTextView, availableBikesTextView;
    private ImageView stationImageView;
    private RecyclerView bikesRecyclerView;
    private BikesAdapter bikesAdapter;
    private List<Bike> bikeList;
    private DatabaseHelper databaseHelper;
    private int stationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

        // Initialize views
        stationNameTextView = findViewById(R.id.stationNameTextView);
        stationAddressTextView = findViewById(R.id.stationAddressTextView);
        availableBikesTextView = findViewById(R.id.availableBikesTextView);
        stationImageView = findViewById(R.id.stationImageView);
        bikesRecyclerView = findViewById(R.id.bikesRecyclerView);

        // Get intent data
        stationId = getIntent().getIntExtra("station_id", 0);
        String stationName = getIntent().getStringExtra("station_name");
        String stationAddress = getIntent().getStringExtra("station_address");

        // Set station info
        stationNameTextView.setText(stationName);
        stationAddressTextView.setText(stationAddress);
        Picasso.get().load(R.drawable.bike_station).into(stationImageView);

        // Setup RecyclerView
        bikeList = new ArrayList<>();
        bikesAdapter = new BikesAdapter(this, bikeList);
        bikesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bikesRecyclerView.setAdapter(bikesAdapter);

        // Load bikes
        databaseHelper = new DatabaseHelper(this);
        loadBikesAtStation(stationName);
    }

    private void loadBikesAtStation(String stationName) {
        bikeList.clear();
        Cursor cursor = databaseHelper.getBikesByLocation(stationName);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Bike bike = new Bike(
                                cursor.getInt(cursor.getColumnIndexOrThrow("bike_id")),
                                cursor.getString(cursor.getColumnIndexOrThrow("bike_name")),
                                cursor.getString(cursor.getColumnIndexOrThrow("bike_type")),
                                cursor.getString(cursor.getColumnIndexOrThrow("bike_status")),
                                cursor.getString(cursor.getColumnIndexOrThrow("bike_location")),
                                cursor.getString(cursor.getColumnIndexOrThrow("bike_image"))
                        );
                        bikeList.add(bike);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }

            // Update available bikes count
            int availableCount = 0;
            for (Bike bike : bikeList) {
                if (bike.getStatus().equals("Available")) {
                    availableCount++;
                }
            }
            availableBikesTextView.setText(availableCount + " bikes available");

            bikesAdapter.notifyDataSetChanged();
        }
    }
}