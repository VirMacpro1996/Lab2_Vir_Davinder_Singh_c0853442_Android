package com.example.dbdemo;

public class ProductModel {
    private int id;
    private String name  , description;
    private double price;

    public ProductModel(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;

        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
