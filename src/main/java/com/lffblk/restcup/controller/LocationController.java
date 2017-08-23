package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.dto.AvgMarkDto;
import com.lffblk.restcup.model.dto.EmptyJsonResponse;
import com.lffblk.restcup.model.dto.LocationDto;
import com.lffblk.restcup.model.entity.User;
import com.lffblk.restcup.model.entity.Visit;
import com.lffblk.restcup.service.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/locations")
public class LocationController {

    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private LocationService locationService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    private IdConverterService idConverterService;

    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}")
    public LocationDto getLocation(@PathVariable String locationId) {
        Integer id = idConverterService.convertId(locationId);
        return modelMapper.map(locationService.getLocationById(id), LocationDto.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}/avg")
    public AvgMarkDto getAvgMark(@PathVariable String locationId,
                                 @RequestParam(value="fromDate", required = false) Long fromDate,
                                 @RequestParam(value="toDate", required = false) Long toDate,
                                 @RequestParam(value="fromAge", required = false) Integer fromAge,
                                 @RequestParam(value="toAge", required = false) Integer toAge,
                                 @RequestParam(value="gender", required = false) String gender) {
        LOG.debug("getAvgMark: locationId = {}, fromDate = {}, toDate = {}, fromAge = {}, toAge = {}, gender = {}",
                locationId, fromDate, toDate, fromAge, toAge, gender);
        Integer id = idConverterService.convertId(locationId);
        // in case location is absent, 404 error will be thrown
        locationService.getLocationById(id);
        List<Visit> visitsByLocation = visitService.getVisits(id, fromDate, toDate);
        LOG.debug("visitsByLocation = {}", visitsByLocation);
        List<Visit> filteredVisits = visitsByLocation.stream().filter(visit -> {
            User user = userService.getUser(visit.getUserId(), fromAge, toAge);
            return user != null && (gender == null || gender.equals(user.getGender()));
        }).collect(Collectors.toList());
        LOG.debug("filteredVisits = {}", filteredVisits);
        OptionalDouble avg = filteredVisits.stream().mapToInt(Visit::getMark).average();
        return avg.isPresent() ? new AvgMarkDto(avg.getAsDouble()) : new AvgMarkDto(0);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{locationId}")
    public ResponseEntity<?> edit(@PathVariable String locationId, @RequestBody LocationDto locationDto) {
        // in case location is absent, 404 error will be thrown
        Integer id = idConverterService.convertId(locationId);
        locationService.getLocationById(id);
        try {
            persistenceService.save(id, locationDto);
            return ResponseEntity.status(HttpStatus.OK).body(new EmptyJsonResponse());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public ResponseEntity<?> add(@RequestBody LocationDto locationDto) {
        try {
            persistenceService.save(null, locationDto);
            return ResponseEntity.status(HttpStatus.OK).body(new EmptyJsonResponse());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
