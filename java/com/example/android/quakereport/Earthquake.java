package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by danielbeleza on 16/02/17.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMiliseconds;
    private String mUrl;

    public Earthquake(double magnitude, String location, long timeInMiliseconds, String url){

        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMiliseconds = timeInMiliseconds;
        this.mUrl = url;
    }

    public void setMagnitude(double magnitude){
        this.mMagnitude = magnitude;
    }

    public double getMagnitude(){
        return this.mMagnitude;
    }

    public void setLocation(String location){
        this.mLocation = location;
    }

    public String getLocation(){
        return mLocation;
    }

    public void setTimeInMiliseconds(long timeInMiliseconds){
        this.mTimeInMiliseconds = timeInMiliseconds;
    }

    public long getTimeInMiliseconds(){
        return mTimeInMiliseconds;
    }

    public void setUrl(String url){
        this.mUrl = url;
    }

    public String getUrl(){
        return this.mUrl;
    }

}
