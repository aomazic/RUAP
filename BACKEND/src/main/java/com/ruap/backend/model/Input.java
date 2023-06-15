package com.ruap.backend.model;


public class Input {
    private int mileage;
    private String make;
    private String model;
    private String fuel;
    private String gear;
    private String  offerType;
    private int hp;
    private int year;

    public int getMileage() {
        return mileage;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getFuel() {
        return fuel;
    }
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
    public String getGear() {
        return gear;
    }
    public void setGear(String gear) {
        this.gear = gear;
    }
    public String getOfferType() {
        return offerType;
    }
    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    @Override
    public String toString() {
        return "Input [mileage=" + mileage + ", make=" + make + ", model=" + model + ", fuel=" + fuel + ", gear=" + gear
                + ", offerType=" + offerType + ", hp=" + hp + ", year=" + year + "]";
    }



}
