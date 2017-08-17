package com.lffblk.restcup.model.dto;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Created by lffblk on 17.08.2017.
 */
public class LocationsDto {

    public LocationsDto() {}

    private List<LocationDto> locations;

    public List<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("locations", locations)
                .toString();
    }
}
