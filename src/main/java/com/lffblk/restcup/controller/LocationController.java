package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.dto.AvgMarkDto;
import com.lffblk.restcup.model.dto.EmptyJsonResponse;
import com.lffblk.restcup.model.dto.LocationDto;
import com.lffblk.restcup.model.entity.Location;
import com.lffblk.restcup.model.entity.User;
import com.lffblk.restcup.model.entity.Visit;
import com.lffblk.restcup.service.LocationService;
import com.lffblk.restcup.service.UserService;
import com.lffblk.restcup.service.VisitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}")
    public LocationDto getLocation(@PathVariable Integer locationId) {
//        return new Location(locationId, 50, "Венесуэлла", "Новоомск","Ресторан");
        return modelMapper.map(locationService.getLocationById(locationId), LocationDto.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}/avg")
    public AvgMarkDto getAvgMark(@PathVariable Integer locationId,
                                 @RequestParam(value="fromDate") Long fromDate,
                                 @RequestParam(value="toDate") Long toDate,
                                 @RequestParam(value="fromAge") Long fromAge,
                                 @RequestParam(value="toAge") Long toAge,
                                 @RequestParam(value="gender") String gender) {
        // in case location is absent, 404 error will be thrown
        locationService.getLocationById(locationId);
        List<Visit> visitsByLocation = visitService.getVisits(locationId, fromDate, toDate);
        List<Visit> filteredVisits = visitsByLocation.stream().filter(visit -> {
            User user = userService.getUser(visit.getUserId(), fromAge, toAge);
            return gender != null && user != null && gender.equals(user.getGender());
        }).collect(Collectors.toList());
        OptionalDouble avg = filteredVisits.stream().mapToInt(Visit::getMark).average();
        return avg.isPresent() ? new AvgMarkDto(avg.getAsDouble()) : new AvgMarkDto(0);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{locationId}")
    public ResponseEntity<?> edit(@PathVariable Integer locationId, @RequestBody LocationDto locationDto) {
        // in case location is absent, 404 error will be thrown
        locationService.getLocationById(locationId);
        try {
            save(locationId, locationDto);
            return ResponseEntity.status(HttpStatus.OK).body(new EmptyJsonResponse());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public ResponseEntity<?> add(@RequestBody LocationDto locationDto) {
        try {
            save(null, locationDto);
            return ResponseEntity.status(HttpStatus.OK).body(new EmptyJsonResponse());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private void save(Integer locationId, LocationDto locationDto) {
        Location updatedLocation = modelMapper.map(locationDto, Location.class);
        if (locationId != null) {
            updatedLocation.setId(locationId);
        }
        locationService.save(updatedLocation);
    }
}
