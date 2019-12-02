package com.example.myride.firebase;

public class RideDetails {
    private RideTime stateDate;
    private RideTime destinationDate;
    private String sourceName;
    private String destinationName;
    private String rideType;
    private int cost;
    private Ridefacility rideFacility;


    public RideDetails(){

    }
    public RideDetails(RideTime sourceDate, RideTime destinationDate, String sourceName,
                       String destinationName, int cost,Ridefacility rideFacility,String rideType) {
        this.stateDate = sourceDate;
        this.destinationDate = destinationDate;
        this.sourceName = sourceName.toLowerCase();
        this.destinationName = destinationName.toLowerCase();
        this.cost = cost;
        this.rideFacility=rideFacility;
        this.rideType = rideType;
    }
    public Ridefacility getRideFacility() {
        return rideFacility;
    }

    public void setRideFacility(Ridefacility rideFacility) {
        this.rideFacility = rideFacility;
    }

    public RideTime getStateDate() {
        return stateDate;
    }

    public void setStateDate(RideTime stateDate) {
        this.stateDate = stateDate;
    }

    public RideTime getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(RideTime destinationDate) {
        this.destinationDate = destinationDate;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName.toLowerCase();
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName.toLowerCase();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }
}
