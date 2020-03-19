package com.example.pleasereserve;

public class Restaurant {

    private String call;
    private String seat;
    private String name;
    private String time;

    public Restaurant(){

    }


    public Restaurant(String name, String call, String seat, String time) {
        this.name=name;
        this.call= call;
        this.seat = seat;
        this.time = time;


    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.call = time;
    }

}
