package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.dto.EmptyJsonResponse;
import com.lffblk.restcup.model.dto.UserDto;
import com.lffblk.restcup.model.dto.UserVisitDto;
import com.lffblk.restcup.model.dto.UserVisitsDto;
import com.lffblk.restcup.model.entity.Location;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    IdConverterService idConverterService;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDto getUser(@PathVariable String userId) {
        Integer id = idConverterService.convertId(userId);
        return modelMapper.map(userService.getUserById(id), UserDto.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/visits")
    public UserVisitsDto getVisit(@PathVariable String userId,
                                  @RequestParam(value="fromDate", required = false) Long fromDate,
                                  @RequestParam(value="toDate", required = false) Long toDate,
                                  @RequestParam(value="country", required = false) String country,
                                  @RequestParam(value="toDistance", required = false) Integer toDistance) {
        Integer id = idConverterService.convertId(userId);
        // in case user is absent, 404 error will be thrown
        userService.getUserById(id);
        List<Visit> visits = visitService.getVisitsByUserIdAndDates(id, fromDate, toDate);
        return new UserVisitsDto(visits.stream()
                .map(visit -> {
                    UserVisitDto userVisitDto = modelMapper.map(visit, UserVisitDto.class);
                    Location location = locationService.getLocation(visit.getLocationId(), country, toDistance);
                    if (location != null) {
                        userVisitDto.setPlace(location.getPlace());
                    }
                    return userVisitDto;
                }).filter(visitDto -> visitDto.getPlace() != null)
                .sorted(Comparator.comparing(UserVisitDto::getDate))
                .collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public ResponseEntity<?> edit(@PathVariable String userId, @RequestBody UserDto userDto) {
        Integer id = idConverterService.convertId(userId);
        // in case location is absent, 404 error will be thrown
        userService.getUserById(id);
        try {
            persistenceService.save(id, userDto);
            return ResponseEntity.status(HttpStatus.OK).body(new EmptyJsonResponse());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public ResponseEntity<?> add(@RequestBody UserDto userDto) {
        LOG.debug("userDto = {}", userDto);
        try {
            persistenceService.save(null, userDto);
            return ResponseEntity.status(HttpStatus.OK).body(new EmptyJsonResponse());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
