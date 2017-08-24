package com.lffblk.restcup.model.entity;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by lffblk on 14.08.2017.
 */
public class Location {
    @NotNull @Id private Integer id;
    @NotNull private Integer distance;
    @NotNull private String country;
    @NotNull private String city;
    @NotNull private String place;

    public Location() {}

    public Location(Integer id, Integer distance, String country, String city, String place) {
        this.id = id;
        this.distance = distance;
        this.country = country;
        this.city = city;
        this.place = place;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
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
