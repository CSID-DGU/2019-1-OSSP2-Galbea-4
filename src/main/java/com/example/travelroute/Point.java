package com.example.travelroute;

public class Point {
    String cor_x, cor_y;
    String pointIndex, description, pointType, totalDistance, totalTime;

    public Point(String cor_x, String cor_y, String pointIndex, String description, String pointType) {
        this.cor_x = cor_x;
        this.cor_y = cor_y;
        this.pointIndex = pointIndex;
        this.description = description;
        this.pointType = pointType;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }


    public String getCor_x() {
        return cor_x;
    }

    public String getCor_y() {
        return cor_y;
    }

    public String getPointIndex() {
        return pointIndex;
    }

    public String getDescription() {
        return description;
    }

    public String getPointType() {
        return pointType;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public String getTotalTime() {
        return totalTime;
    }
}
