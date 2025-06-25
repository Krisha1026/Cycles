package com.example.citycyclerentals;

public class Bike {
    private int id;
    private String name;
    private String type;
    private String status;
    private String location;
    private String image;

    public Bike(int id, String name, String type, String status, String location, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
        this.image = image;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public String getLocation() { return location; }
    public String getImage() { return image; }
}
