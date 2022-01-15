package com.upgrad.vehicle.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "vehicles")
public class Vehicle {
    private String vehicleId;
    private String vehicleModel;
    private String vehicleNumber;
    private String colour;
    private String availability;
    private String vehicleDriver;
    private String priceperday;

    public Vehicle() {
    }

    @DynamoDBHashKey(attributeName = "vehicleId")
    @DynamoDBAutoGeneratedKey
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @DynamoDBAttribute(attributeName = "vehicleModel")
    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    @DynamoDBAttribute(attributeName = "vehicleNumber")
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @DynamoDBAttribute(attributeName = "colour")
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @DynamoDBAttribute(attributeName = "availability")
    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @DynamoDBAttribute(attributeName = "vehicleDriver")
    public String getVehicleDriver() {
        return vehicleDriver;
    }

    public void setVehicleDriver(String vehicleDriver) {
        this.vehicleDriver = vehicleDriver;
    }

    @DynamoDBAttribute(attributeName = "priceperday")
    public String getPriceperday() {
        return priceperday;
    }

    public void setPriceperday(String priceperday) {
        this.priceperday = priceperday;
    }
}
