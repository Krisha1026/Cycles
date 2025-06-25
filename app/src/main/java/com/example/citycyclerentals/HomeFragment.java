package com.example.citycyclerentals;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private RecyclerView stationsRecyclerView;
    private StationsAdapter stationsAdapter;
    private List<Station> stationList;
    private DatabaseHelper databaseHelper;
    private Button viewAllStationsButton, searchButton;
    private Spinner citySpinner;
    private TextView startDateText, endDateText, durationText;
    private Calendar startCalendar, endCalendar;
    private SimpleDateFormat dateTimeFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize database and lists
        databaseHelper = new DatabaseHelper(getActivity());
        stationList = new ArrayList<>();
        dateTimeFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a", Locale.getDefault());

        // Initialize views
        initializeViews(view);

        // Setup RecyclerView
        setupRecyclerView();


        setupCitySpinner();

        // Initialize date calendars
        initializeDateCalendars();

        // Set click listeners
        setClickListeners();

        // Load initial station data
        loadNearbyStations();

        return view;
    }

    private void initializeViews(View view) {
        stationsRecyclerView = view.findViewById(R.id.stationsRecyclerView);
        viewAllStationsButton = view.findViewById(R.id.viewAllStationsButton);
        searchButton = view.findViewById(R.id.searchButton);
        citySpinner = view.findViewById(R.id.citySpinner);
        startDateText = view.findViewById(R.id.startDateText);
        endDateText = view.findViewById(R.id.endDateText);
        durationText = view.findViewById(R.id.durationText);
    }

    private void setupRecyclerView() {
        stationsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stationsAdapter = new StationsAdapter(getActivity(), stationList);
        stationsRecyclerView.setAdapter(stationsAdapter);
    }

    private void setupCitySpinner() {
        // Sri Lankan cities
        List<String> cities = new ArrayList<>();
        cities.add("Colombo");
        cities.add("Kandy");
        cities.add("Galle");
        cities.add("Jaffna");
        cities.add("Negombo");
        cities.add("Trincomalee");

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                cities
        );
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
    }

    private void initializeDateCalendars() {
        startCalendar = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DAY_OF_MONTH, 1); // Default to next day
        updateDateTextViews();
    }

    private void setClickListeners() {
        viewAllStationsButton.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), AllStationsActivity.class)));

        searchButton.setOnClickListener(v -> performSearch());

        startDateText.setOnClickListener(v -> showDateTimePicker(true));
        endDateText.setOnClickListener(v -> showDateTimePicker(false));
    }

    private void showDateTimePicker(final boolean isStartDate) {
        final Calendar calendar = isStartDate ? startCalendar : endCalendar;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Date Picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Time Picker
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            getActivity(),
                            (view1, selectedHour, selectedMinute) -> {
                                calendar.set(selectedYear, selectedMonth, selectedDay,
                                        selectedHour, selectedMinute);
                                updateDateTextViews();

                                //  end date is after start date
                                if (isStartDate && endCalendar.before(startCalendar)) {
                                    endCalendar.setTimeInMillis(startCalendar.getTimeInMillis());
                                    endCalendar.add(Calendar.DAY_OF_MONTH, 1);
                                    updateDateTextViews();
                                }
                            },
                            hour, minute, false
                    );
                    timePickerDialog.show();
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void updateDateTextViews() {
        startDateText.setText(dateTimeFormat.format(startCalendar.getTime()));
        endDateText.setText(dateTimeFormat.format(endCalendar.getTime()));

        // Calculate and display duration
        long diffInMillis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);
        durationText.setText(String.format(Locale.getDefault(),
                "Duration: %d Day%s", diffInDays, diffInDays != 1 ? "s" : ""));
    }

    private void performSearch() {
        String selectedCity = citySpinner.getSelectedItem().toString();
        String startDate = dateTimeFormat.format(startCalendar.getTime());
        String endDate = dateTimeFormat.format(endCalendar.getTime());
        long durationDays = (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / (1000 * 60 * 60 * 24);

        Intent intent = new Intent(getActivity(), AllStationsActivity.class);
        intent.putExtra("search_mode", "filtered");
        intent.putExtra("city", selectedCity);
        intent.putExtra("start_date", startDate);
        intent.putExtra("end_date", endDate);
        intent.putExtra("duration", durationDays);
        startActivity(intent);
    }

    private void loadNearbyStations() {
        stationList.clear();
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