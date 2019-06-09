package com.example.travelroute;

public class GetAPI {
    int lat;
    int ion;
    String location;
    String name;

    public void setLat(int lat){ this.lat = lat;}
    public void setIon(int ion){ this.ion = ion;}

    public void setLocation(String location) { this.location = location; }

    public void setName(String name) { this.name = name; }

    public int getLat(){ return lat;}
    public int getIon() { return ion;}
    public String getLocation() {return location;}
    public String getName(){return name;}
}


