package com.lffblk.restcup.service;

import com.lffblk.restcup.exception.EntityNotFoundException;
import com.lffblk.restcup.model.dto.LocationDto;
import com.lffblk.restcup.model.dto.UserDto;
import com.lffblk.restcup.model.dto.VisitDto;
import com.lffblk.restcup.model.entity.Location;
import com.lffblk.restcup.model.entity.User;
import com.lffblk.restcup.model.entity.Visit;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lffblk on 17.08.2017.
 */
@Service
public class PersistenceService {

    private final static Logger LOG = LoggerFactory.getLogger(PersistenceService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private VisitService visitService;

    public void save(Integer locationId, LocationDto locationDto) {
        LOG.trace("save, locationId = {}, locationDto = {}", locationId, locationDto);
        Location updatedLocation = modelMapper.map(locationDto, Location.class);
        if (locationId != null) {
            Location existingLocation = locationService.getLocationById(locationId);
            if (updatedLocation.getId() == -1) {
                updatedLocation.setId(existingLocation.getId());
            }
            if (updatedLocation.getDistance() == -1) {
                updatedLocation.setDistance(existingLocation.getDistance());
            }
            if (updatedLocation.getCity() != null && updatedLocation.getCity().isEmpty()) {
                updatedLocation.setCity(existingLocation.getCity());
            }
            if (updatedLocation.getCountry() != null && updatedLocation.getCountry().isEmpty()) {
                updatedLocation.setCountry(existingLocation.getCountry());
            }
            if (updatedLocation.getPlace() != null && updatedLocation.getPlace().isEmpty()) {
                updatedLocation.setPlace(existingLocation.getPlace());
            }
        }
        LOG.trace("updatedLocation = {}", updatedLocation);
        locationService.save(updatedLocation);
    }

    public void save(Integer userId, UserDto userDto) {
        LOG.trace("save, userId = {}, userDto = {}", userId, userDto);
        User updatedUser = modelMapper.map(userDto, User.class);
        if (userId != null) {
            User existingUser = userService.getUserById(userId);
            if (updatedUser.getId() == -1) {
                updatedUser.setId(existingUser.getId());
            }
            if (updatedUser.getBirthDate() == -1) {
                updatedUser.setBirthDate(existingUser.getBirthDate());
            }
            if (updatedUser.getEmail() != null && updatedUser.getEmail().isEmpty()) {
                updatedUser.setEmail(existingUser.getEmail());
            }
            if (updatedUser.getFirstName() != null && updatedUser.getFirstName().isEmpty()) {
                updatedUser.setFirstName(existingUser.getFirstName());
            }
            if (updatedUser.getLastName() != null && updatedUser.getLastName().isEmpty()) {
                updatedUser.setLastName(existingUser.getLastName());
            }
            if (updatedUser.getGender() != null && updatedUser.getGender().isEmpty()) {
                updatedUser.setGender(existingUser.getGender());
            }
        }
        LOG.trace("updatedUser = {}", updatedUser);
        userService.save(updatedUser);
    }

    public void save(Integer visitId, VisitDto visitDto) {
        LOG.trace("save, visitId = {}, visitDto = {}", visitId, visitDto);
        Visit updatedVisit = modelMapper.map(visitDto, Visit.class);
        if (visitId != null) {
            Visit existingVisit = visitService.getVisitById(visitId);
            if (updatedVisit.getId() == -1) {
                updatedVisit.setId(visitId);
            }
            if (updatedVisit.getUserId() == -1) {
                updatedVisit.setUserId(existingVisit.getUserId());
            }
            if (updatedVisit.getLocationId() == -1) {
                updatedVisit.setLocationId(existingVisit.getLocationId());
            }
            if (updatedVisit.getMark() == -1) {
                updatedVisit.setMark(existingVisit.getMark());
            }
            if (updatedVisit.getDate() == -1) {
                updatedVisit.setDate(existingVisit.getDate());
            }
        }
        LOG.trace("updatedVisit = {}", updatedVisit);
        visitService.save(updatedVisit);
    }
}
