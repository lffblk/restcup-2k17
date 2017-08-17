package com.lffblk.restcup.service;

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
        LOG.debug("save, locationId = {}, locationDto = {}", locationId, locationDto);
        Location updatedLocation = modelMapper.map(locationDto, Location.class);
        if (locationId != null) {
            updatedLocation.setId(locationId);
        }
        locationService.save(updatedLocation);
        LOG.debug("updatedLocation = {}", updatedLocation);
    }

    public void save(Integer userId, UserDto userDto) {
        LOG.debug("save, userId = {}, userDto = {}", userId, userDto);
        User updatedUser = modelMapper.map(userDto, User.class);
        if (userId != null) {
            updatedUser.setId(userId);
        }
        userService.save(updatedUser);
        LOG.debug("updatedUser = {}", updatedUser);
    }

    public void save(Integer visitId, VisitDto visitDto) {
        LOG.debug("save, visitId = {}, visitDto = {}", visitId, visitDto);
        Visit updatedVisit = modelMapper.map(visitDto, Visit.class);
        if (visitId != null) {
            updatedVisit.setId(visitId);
        }
        visitService.save(updatedVisit);
        LOG.debug("updatedVisit = {}", updatedVisit);
    }
}
