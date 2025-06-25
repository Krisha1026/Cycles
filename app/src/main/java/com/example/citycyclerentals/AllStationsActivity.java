package com.example.citycyclerentals;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AllStationsActivity extends AppCompatActivity {
    private RecyclerView stationsRecyclerView;
    private StationsAdapter stationsAdapter;
    private List<Station> stationList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stations);

        databaseHelper = new DatabaseHelper(this);
        stationList = new ArrayList<>();

        stationsRecyclerView = findViewById(R.id.stationsRecyclerView);
        stationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stationsAdapter = new StationsAdapter(this, stationList);
        stationsRecyclerView.setAdapter(stationsAdapter);

        loadAllStations();
    }

    private void loadAllStations() {
        Cursor cursor = databaseHelper.getAllStations();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("station_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("station_name"));
                double lat = cursor.getDouble(cursor.getColumnIndexOrThrow("station_lat"));
                double lng = cursor.getDouble(cursor.getColumnIndexOrThrow("station_lng"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("station_address"));

                Station station = new Station(id, name, lat, lng, address);
                stationList.add(station);
            } while (cursor.moveToNext());
            cursor.close();
            stationsAdapter.notifyDataSetChanged();
        }
    }
}