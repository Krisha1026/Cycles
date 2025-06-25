package com.example.citycyclerentals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CityCycleRentals.db";
    private static final int DATABASE_VERSION = 1;

    // User table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_PHONE = "user_phone";

    // Bikes table
    private static final String TABLE_BIKES = "bikes";
    private static final String COLUMN_BIKE_ID = "bike_id";
    private static final String COLUMN_BIKE_NAME = "bike_name";
    private static final String COLUMN_BIKE_TYPE = "bike_type";
    private static final String COLUMN_BIKE_STATUS = "bike_status";
    private static final String COLUMN_BIKE_LOCATION = "bike_location";
    private static final String COLUMN_BIKE_IMAGE = "bike_image";

    // Stations table
    private static final String TABLE_STATIONS = "stations";
    private static final String COLUMN_STATION_ID = "station_id";
    private static final String COLUMN_STATION_NAME = "station_name";
    private static final String COLUMN_STATION_LAT = "station_lat";
    private static final String COLUMN_STATION_LNG = "station_lng";
    private static final String COLUMN_STATION_ADDRESS = "station_address";

    // Rentals table
    private static final String TABLE_RENTALS = "rentals";
    private static final String COLUMN_RENTAL_ID = "rental_id";
    private static final String COLUMN_RENTAL_USER_ID = "user_id";
    private static final String COLUMN_RENTAL_BIKE_ID = "bike_id";
    private static final String COLUMN_RENTAL_START_TIME = "start_time";
    private static final String COLUMN_RENTAL_END_TIME = "end_time";
    private static final String COLUMN_RENTAL_PRICE = "price";
    private static final String COLUMN_RENTAL_STATUS = "status";

    // Payments table
    private static final String TABLE_PAYMENTS = "payments";
    private static final String COLUMN_PAYMENT_ID = "payment_id";
    private static final String COLUMN_PAYMENT_USER_ID = "user_id";
    private static final String COLUMN_PAYMENT_RENTAL_ID = "rental_id";
    private static final String COLUMN_PAYMENT_AMOUNT = "amount";
    private static final String COLUMN_PAYMENT_METHOD = "method";
    private static final String COLUMN_PAYMENT_DATE = "date";
    private static final String COLUMN_PAYMENT_STATUS = "status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT UNIQUE,"
                + COLUMN_USER_PASSWORD + " TEXT,"
                + COLUMN_USER_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        // Create bikes table
        String CREATE_BIKES_TABLE = "CREATE TABLE " + TABLE_BIKES + "("
                + COLUMN_BIKE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_BIKE_NAME + " TEXT,"
                + COLUMN_BIKE_TYPE + " TEXT,"
                + COLUMN_BIKE_STATUS + " TEXT,"
                + COLUMN_BIKE_LOCATION + " TEXT,"
                + COLUMN_BIKE_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_BIKES_TABLE);

        // Create stations table
        String CREATE_STATIONS_TABLE = "CREATE TABLE " + TABLE_STATIONS + "("
                + COLUMN_STATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_STATION_NAME + " TEXT,"
                + COLUMN_STATION_LAT + " REAL,"
                + COLUMN_STATION_LNG + " REAL,"
                + COLUMN_STATION_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_STATIONS_TABLE);

        // Create rentals table
        String CREATE_RENTALS_TABLE = "CREATE TABLE " + TABLE_RENTALS + "("
                + COLUMN_RENTAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RENTAL_USER_ID + " INTEGER,"
                + COLUMN_RENTAL_BIKE_ID + " INTEGER,"
                + COLUMN_RENTAL_START_TIME + " TEXT,"
                + COLUMN_RENTAL_END_TIME + " TEXT,"
                + COLUMN_RENTAL_PRICE + " REAL,"
                + COLUMN_RENTAL_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_RENTALS_TABLE);

        // Create payments table
        String CREATE_PAYMENTS_TABLE = "CREATE TABLE " + TABLE_PAYMENTS + "("
                + COLUMN_PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PAYMENT_USER_ID + " INTEGER,"
                + COLUMN_PAYMENT_RENTAL_ID + " INTEGER,"
                + COLUMN_PAYMENT_AMOUNT + " REAL,"
                + COLUMN_PAYMENT_METHOD + " TEXT,"
                + COLUMN_PAYMENT_DATE + " TEXT,"
                + COLUMN_PAYMENT_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_PAYMENTS_TABLE);

        // Insert sample data
        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Insert sample stations
        ContentValues stationValues = new ContentValues();
        // Colombo stations
        stationValues.put(COLUMN_STATION_NAME, "Colombo Fort Station");
        stationValues.put(COLUMN_STATION_LAT, 6.9344);
        stationValues.put(COLUMN_STATION_LNG, 79.8428);
        stationValues.put(COLUMN_STATION_ADDRESS, "Colombo Fort, Colombo 01");
        db.insert(TABLE_STATIONS, null, stationValues);

        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Galle Face Station");
        stationValues.put(COLUMN_STATION_LAT, 6.9271);
        stationValues.put(COLUMN_STATION_LNG, 79.8412);
        stationValues.put(COLUMN_STATION_ADDRESS, "Galle Face Green, Colombo 03");
        db.insert(TABLE_STATIONS, null, stationValues);

        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Pettah Market Station");
        stationValues.put(COLUMN_STATION_LAT, 6.9369);
        stationValues.put(COLUMN_STATION_LNG, 79.8531);
        stationValues.put(COLUMN_STATION_ADDRESS, "Pettah, Colombo 11");
        db.insert(TABLE_STATIONS, null, stationValues);

        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Bambalapitiya Station");
        stationValues.put(COLUMN_STATION_LAT, 6.8958);
        stationValues.put(COLUMN_STATION_LNG, 79.8547);
        stationValues.put(COLUMN_STATION_ADDRESS, "Bambalapitiya, Colombo 04");
        db.insert(TABLE_STATIONS, null, stationValues);

        // Kandy stations
        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Kandy City Center Station");
        stationValues.put(COLUMN_STATION_LAT, 7.2964);
        stationValues.put(COLUMN_STATION_LNG, 80.6350);
        stationValues.put(COLUMN_STATION_ADDRESS, "City Center, Kandy");
        db.insert(TABLE_STATIONS, null, stationValues);

        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Temple of the Tooth Station");
        stationValues.put(COLUMN_STATION_LAT, 7.2936);
        stationValues.put(COLUMN_STATION_LNG, 80.6414);
        stationValues.put(COLUMN_STATION_ADDRESS, "Sri Dalada Veediya, Kandy");
        db.insert(TABLE_STATIONS, null, stationValues);

        // Galle stations
        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Galle Fort Station");
        stationValues.put(COLUMN_STATION_LAT, 6.0259);
        stationValues.put(COLUMN_STATION_LNG, 80.2167);
        stationValues.put(COLUMN_STATION_ADDRESS, "Galle Fort, Galle");
        db.insert(TABLE_STATIONS, null, stationValues);

        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Unawatuna Beach Station");
        stationValues.put(COLUMN_STATION_LAT, 6.0177);
        stationValues.put(COLUMN_STATION_LNG, 80.2497);
        stationValues.put(COLUMN_STATION_ADDRESS, "Unawatuna Beach Road, Unawatuna");
        db.insert(TABLE_STATIONS, null, stationValues);

        // Jaffna station
        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Jaffna Public Library Station");
        stationValues.put(COLUMN_STATION_LAT, 9.6647);
        stationValues.put(COLUMN_STATION_LNG, 80.0167);
        stationValues.put(COLUMN_STATION_ADDRESS, "Jaffna Public Library, Jaffna");
        db.insert(TABLE_STATIONS, null, stationValues);

        // Negombo station
        stationValues = new ContentValues();
        stationValues.put(COLUMN_STATION_NAME, "Negombo Beach Station");
        stationValues.put(COLUMN_STATION_LAT, 7.2111);
        stationValues.put(COLUMN_STATION_LNG, 79.8386);
        stationValues.put(COLUMN_STATION_ADDRESS, "Negombo Beach, Negombo");
        db.insert(TABLE_STATIONS, null, stationValues);

        // Insert Sri Lanka bikes
        ContentValues bikeValues = new ContentValues();
        bikeValues.put(COLUMN_BIKE_NAME, "Colombo City Bike 001");
        bikeValues.put(COLUMN_BIKE_TYPE, "Standard");
        bikeValues.put(COLUMN_BIKE_STATUS, "Available");
        bikeValues.put(COLUMN_BIKE_LOCATION, "Colombo Fort Station");
        bikeValues.put(COLUMN_BIKE_IMAGE, "bike_standard");
        db.insert(TABLE_BIKES, null, bikeValues);

        bikeValues = new ContentValues();
        bikeValues.put(COLUMN_BIKE_NAME, "Galle Face  Bike 002");
        bikeValues.put(COLUMN_BIKE_TYPE, "Standard");
        bikeValues.put(COLUMN_BIKE_STATUS, "Available");
        bikeValues.put(COLUMN_BIKE_LOCATION, "Galle Face Station");
        bikeValues.put(COLUMN_BIKE_IMAGE, "bike_mountain");
        db.insert(TABLE_BIKES, null, bikeValues);

        bikeValues = new ContentValues();
        bikeValues.put(COLUMN_BIKE_NAME, "Pettah ride  Bike 002");
        bikeValues.put(COLUMN_BIKE_TYPE, "Standard");
        bikeValues.put(COLUMN_BIKE_STATUS, "Available");
        bikeValues.put(COLUMN_BIKE_LOCATION, "Pettah Market Station");
        bikeValues.put(COLUMN_BIKE_IMAGE, "bike_mountain");
        db.insert(TABLE_BIKES, null, bikeValues);

        bikeValues = new ContentValues();
        bikeValues.put(COLUMN_BIKE_NAME, "Bambalapitiya king  Bike 003");
        bikeValues.put(COLUMN_BIKE_TYPE, "Standard");
        bikeValues.put(COLUMN_BIKE_STATUS, "Available");
        bikeValues.put(COLUMN_BIKE_LOCATION, "Bambalapitiya Station");
        bikeValues.put(COLUMN_BIKE_IMAGE, "bike_mountain");
        db.insert(TABLE_BIKES, null, bikeValues);




        bikeValues = new ContentValues();
        bikeValues.put(COLUMN_BIKE_NAME, "Kandy Mountain Bike 004");
        bikeValues.put(COLUMN_BIKE_TYPE, "Mountain");
        bikeValues.put(COLUMN_BIKE_STATUS, "Available");
        bikeValues.put(COLUMN_BIKE_LOCATION, "Temple of the Tooth Station");
        bikeValues.put(COLUMN_BIKE_IMAGE, "bike_mountain");
        db.insert(TABLE_BIKES, null, bikeValues);

        bikeValues = new ContentValues();
        bikeValues.put(COLUMN_BIKE_NAME, "Galle Beach Cruiser 005");
        bikeValues.put(COLUMN_BIKE_TYPE, "Cruiser");
        bikeValues.put(COLUMN_BIKE_STATUS, "Available");
        bikeValues.put(COLUMN_BIKE_LOCATION, "Galle Fort Station");
        bikeValues.put(COLUMN_BIKE_IMAGE, "bike_cruiser");
        db.insert(TABLE_BIKES, null, bikeValues);

        bikeValues = new ContentValues();
        bikeValues.put(COLUMN_BIKE_NAME, "Negombo Hybrid 006");
        bikeValues.put(COLUMN_BIKE_TYPE, "Hybrid");
        bikeValues.put(COLUMN_BIKE_STATUS, "Available");
        bikeValues.put(COLUMN_BIKE_LOCATION, "Negombo Beach Station");
        bikeValues.put(COLUMN_BIKE_IMAGE, "bike_hybrid");
        db.insert(TABLE_BIKES, null, bikeValues);

        // Insert sample Sri Lanka user
        ContentValues userValues = new ContentValues();
        userValues.put(COLUMN_USER_NAME, "Krisha");
        userValues.put(COLUMN_USER_EMAIL, "krisha@gmail.lk");
        userValues.put(COLUMN_USER_PASSWORD, "123456");
        userValues.put(COLUMN_USER_PHONE, "+94112345678");
        db.insert(TABLE_USERS, null, userValues);

        userValues = new ContentValues();
        userValues.put(COLUMN_USER_NAME, "Rama");
        userValues.put(COLUMN_USER_EMAIL, "rama@gmail.com");
        userValues.put(COLUMN_USER_PASSWORD, "123456");
        userValues.put(COLUMN_USER_PHONE, "+94802345678");
        db.insert(TABLE_USERS, null, userValues);

        userValues = new ContentValues();
        userValues.put(COLUMN_USER_NAME, "Nisha");
        userValues.put(COLUMN_USER_EMAIL, "nisha@gmail.lk");
        userValues.put(COLUMN_USER_PASSWORD, "123456");
        userValues.put(COLUMN_USER_PHONE, "+94112995678");
        db.insert(TABLE_USERS, null, userValues);

        // Insert sample rental in Sri Lanka
        ContentValues rentalValues = new ContentValues();
        rentalValues.put(COLUMN_RENTAL_USER_ID, 1);
        rentalValues.put(COLUMN_RENTAL_BIKE_ID, 1);
        rentalValues.put(COLUMN_RENTAL_START_TIME, "2025-03-01 10:00:00");
        rentalValues.put(COLUMN_RENTAL_END_TIME, "2025-03-01 12:30:00");
        rentalValues.put(COLUMN_RENTAL_STATUS, "Completed");
        rentalValues.put(COLUMN_RENTAL_PRICE, 750.00); // LKR
        db.insert(TABLE_RENTALS, null, rentalValues);

        rentalValues = new ContentValues();
        rentalValues.put(COLUMN_RENTAL_USER_ID, 2);
        rentalValues.put(COLUMN_RENTAL_BIKE_ID, 4);
        rentalValues.put(COLUMN_RENTAL_START_TIME, "2025-03-07 10:00:00");
        rentalValues.put(COLUMN_RENTAL_END_TIME, "2025-03-07 12:30:00");
        rentalValues.put(COLUMN_RENTAL_STATUS, "Completed");
        rentalValues.put(COLUMN_RENTAL_PRICE, 750.00); // LKR
        db.insert(TABLE_RENTALS, null, rentalValues);

        // Insert sample payment
        ContentValues paymentValues = new ContentValues();
        paymentValues.put(COLUMN_PAYMENT_USER_ID, 1);
        paymentValues.put(COLUMN_PAYMENT_RENTAL_ID, 1);
        paymentValues.put(COLUMN_PAYMENT_AMOUNT, 750.00);
        paymentValues.put(COLUMN_PAYMENT_METHOD, "Credit Card");
        paymentValues.put(COLUMN_PAYMENT_DATE, "2025-03-01 12:35:00");
        paymentValues.put(COLUMN_PAYMENT_STATUS, "Completed");
        db.insert(TABLE_PAYMENTS, null, paymentValues);

        paymentValues = new ContentValues();
        paymentValues.put(COLUMN_PAYMENT_USER_ID, 2);
        paymentValues.put(COLUMN_PAYMENT_RENTAL_ID, 2);
        paymentValues.put(COLUMN_PAYMENT_AMOUNT, 750.00);
        paymentValues.put(COLUMN_PAYMENT_METHOD, "Credit Card");
        paymentValues.put(COLUMN_PAYMENT_DATE, "2025-03-07 12:35:00");
        paymentValues.put(COLUMN_PAYMENT_STATUS, "Completed");
        db.insert(TABLE_PAYMENTS, null, paymentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIKES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RENTALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENTS);
        onCreate(db);
    }

    // User methods
    public boolean addUser(String name, String email, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_PHONE, phone);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER_ID};
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // Bike methods
    public Cursor getAllBikes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_BIKES, null, null, null, null, null, null);
    }

    public Cursor getBikesByLocation(String location) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_BIKE_LOCATION + " = ?";
        String[] selectionArgs = {location};
        return db.query(TABLE_BIKES, null, selection, selectionArgs, null, null, null);
    }

    // Station methods
    public Cursor getAllStations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_STATIONS, null, null, null, null, null, null);
    }

    // Rental methods
    public Cursor getAllRentals() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_RENTALS,
                null,
                null,
                null,
                null,
                null,
                COLUMN_RENTAL_START_TIME + " DESC"
        );
    }

    public Cursor getRentalsByUserId(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_RENTAL_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        return db.query(
                TABLE_RENTALS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                COLUMN_RENTAL_START_TIME + " DESC"
        );
    }

    public boolean startRental(int userId, int bikeId, String startTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RENTAL_USER_ID, userId);
        values.put(COLUMN_RENTAL_BIKE_ID, bikeId);
        values.put(COLUMN_RENTAL_START_TIME, startTime);
        values.put(COLUMN_RENTAL_STATUS, "Active");

        long result = db.insert(TABLE_RENTALS, null, values);

        if (result != -1) {
            // Update bike status
            ContentValues bikeValues = new ContentValues();
            bikeValues.put(COLUMN_BIKE_STATUS, "Rented");
            String whereClause = COLUMN_BIKE_ID + " = ?";
            String[] whereArgs = {String.valueOf(bikeId)};
            db.update(TABLE_BIKES, bikeValues, whereClause, whereArgs);
            return true;
        }
        return false;
    }

    public boolean endRental(int rentalId, int bikeId, String endTime, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RENTAL_END_TIME, endTime);
        values.put(COLUMN_RENTAL_PRICE, price);
        values.put(COLUMN_RENTAL_STATUS, "Completed");

        String whereClause = COLUMN_RENTAL_ID + " = ?";
        String[] whereArgs = {String.valueOf(rentalId)};

        int result = db.update(TABLE_RENTALS, values, whereClause, whereArgs);

        if (result > 0) {
            // Update bike status
            ContentValues bikeValues = new ContentValues();
            bikeValues.put(COLUMN_BIKE_STATUS, "Available");
            String bikeWhereClause = COLUMN_BIKE_ID + " = ?";
            String[] bikeWhereArgs = {String.valueOf(bikeId)};
            db.update(TABLE_BIKES, bikeValues, bikeWhereClause, bikeWhereArgs);
            return true;
        }
        return false;
    }



    // Payment methods
    public boolean addPayment(int userId, int rentalId, double amount, String method, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAYMENT_USER_ID, userId);
        values.put(COLUMN_PAYMENT_RENTAL_ID, rentalId);
        values.put(COLUMN_PAYMENT_AMOUNT, amount);
        values.put(COLUMN_PAYMENT_METHOD, method);
        values.put(COLUMN_PAYMENT_DATE, date);
        values.put(COLUMN_PAYMENT_STATUS, "Completed");

        long result = db.insert(TABLE_PAYMENTS, null, values);
        return result != -1;
    }
}