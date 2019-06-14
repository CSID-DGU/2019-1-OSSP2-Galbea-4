package com.example.travelroute;

public class GetAPI {
    double lat;
    double ion;
    String location;
    String name;
    String category;
    int score;


    public void setLat(double lat){ this.lat = lat;}
    public void setIon(double ion){ this.ion = ion;}
    public void setCategory(String Category){this.category = category;}
    public void setLocation(String location) { this.location = location; }
    public void setName(String name) { this.name = name; }
    public void setScore(int score ){ this.score = score;}

    public double getLat(){ return lat;}
    public double getIon() { return ion;}
    public int getScore() {return score;}
    public String getCategory(){return category;}
    public String getLocation() {return location;}
    public String getName(){return name;}
}


