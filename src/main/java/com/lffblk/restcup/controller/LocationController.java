package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.Location;
import com.lffblk.restcup.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}")
    public Location getLocation(@PathVariable Integer locationId) {
//        return new Location(locationId, 50, "Венесуэлла", "Новоомск","Ресторан");
        return locationRepository.findOne(locationId);
    }
}
