package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.dto.UserDto;
import com.lffblk.restcup.model.dto.UserVisitDto;
import com.lffblk.restcup.model.dto.UserVisitsDto;
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
import java.util.stream.Collectors;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDto getUser(@PathVariable Integer userId) {
//        return new User(userId, "Vasya", "Pupkin", "m", -73267200, "ygothepewoegidno@me.com");
        return modelMapper.map(userService.getUserById(userId), UserDto.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/visits")
    public UserVisitsDto getVisit(@PathVariable Integer userId,
                                  @RequestParam(value="fromDate") Long fromDate,
                                  @RequestParam(value="toDate") Long toDate,
                                  @RequestParam(value="country") String country,
                                  @RequestParam(value="toDistance") Integer toDistance) {
//        return new Visit(visitId, 148, 144, 1002632924, 4);
        // in case user is absent, 404 error will be thrown
        userService.getUserById(userId);
        List<Visit> visits = visitService.getVisitsByUserIdAndDates(userId, fromDate, toDate);
        return new UserVisitsDto(visits.stream().map(visit -> {
            UserVisitDto userVisitDto = modelMapper.map(visit, UserVisitDto.class);
            Location location = locationService.getLocation(visit.getLocationId(), country, toDistance);
            if (location != null) {
                userVisitDto.setPlace(location.getPlace());
            }
            return userVisitDto;
        }).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public ResponseEntity<?> edit(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        // in case location is absent, 404 error will be thrown
        userService.getUserById(userId);
        try {
            save(userId, userDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public ResponseEntity<?> add(@RequestBody UserDto userDto) {
        System.out.println("HERE!!!!!");
        try {
            save(null, userDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private void save(Integer userId, UserDto userDto) {
        User updatedUser = modelMapper.map(userDto, User.class);
        if (userId != null) {
            updatedUser.setId(userId);
        }
        userService.save(updatedUser);
    }
}
