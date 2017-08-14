package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.Location;
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

    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}")
    public Location getLocation(@PathVariable String locationId) {
        return new Location(Integer.parseInt(locationId), 50, "Венесуэлла", "Новоомск",
                "Ресторан");
    }
}
