package com.lffblk.restcup.model.dto;

import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 15.08.2017.
 */
public class LocationDto {
    private int id;
    private int distance;
    private String country;
    private String city;
    private String place;

    public LocationDto(int id, int distance, String country, String city, String place) {
        this.id = id;
        this.distance = distance;
        this.country = country;
        this.city = city;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("distance", distance)
                .add("country", country)
                .add("city", city)
                .add("place", place)
                .toString();
    }
}
