package com.example.citycyclerentals;

public class Station {
    private int id;
    private String name;
    private double lat;
    private double lng;
    private String address;

    public Station(int id, String name, double lat, double lng, String address) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getAddress() { return address; }
}