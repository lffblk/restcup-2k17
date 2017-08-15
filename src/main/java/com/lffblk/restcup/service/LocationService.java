package com.lffblk.restcup.service;

import com.lffblk.restcup.exception.EntityNotFoundException;
import com.lffblk.restcup.model.entity.Location;
import com.lffblk.restcup.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lffblk on 15.08.2017.
 */
@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location getLocationById(Integer id) {
        return getLocation(id);
    }

    public Location getLocation(Integer id, String country, Integer toDistance) {
        if (country == null && toDistance == null) {
            return getLocation(id);
        }
        else if (country == null) {
            return locationRepository.findByIdAndDistanceBefore(id, toDistance);
        }
        else if (toDistance == null) {
            return locationRepository.findByIdAndCountry(id, country);
        }
        else {
            return locationRepository.findByIdAndCountryAndDistanceBefore(id, country, toDistance);
        }
    }

    private Location getLocation(Integer id) {
        Location location = locationRepository.findOne(id);
        if (location == null) {
            throw new EntityNotFoundException("Location with id " + id + " was not found");
        }
        return location;
    }

    public void save(Location location) {
        locationRepository.save(location);
    }
}
