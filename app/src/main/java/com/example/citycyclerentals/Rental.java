package com.example.citycyclerentals;

public class Rental {
    private int id;
    private int userId;
    private int bikeId;
    private String startTime;
    private String endTime;
    private double price;
    private String status;

    public Rental(int id, int userId, int bikeId, String startTime, String endTime, double price, String status) {
        this.id = id;
        this.userId = userId;
        this.bikeId = bikeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.status = status;
    }

    // Getters and setters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getBikeId() { return bikeId; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
}
