package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.dto.VisitDto;
import com.lffblk.restcup.model.entity.Visit;
import com.lffblk.restcup.service.LocationService;
import com.lffblk.restcup.service.VisitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{visitId}")
    public VisitDto getVisit(@PathVariable Integer visitId) {
//        return new Visit(visitId, 148, 144, 1002632924, 4);
        return modelMapper.map(visitService.getVisitById(visitId), VisitDto.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{visitId}")
    public ResponseEntity<?> edit(@PathVariable Integer visitId, @RequestBody VisitDto visitDto) {
        // in case location is absent, 404 error will be thrown
        visitService.getVisitById(visitId);
        try {
            save(visitId, visitDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public ResponseEntity<?> add(@RequestBody VisitDto visitDto) {
        try {
            save(null, visitDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private void save(Integer visitId, VisitDto visitDto) {
        Visit updatedVisit = modelMapper.map(visitDto, Visit.class);
        if (visitId != null) {
            updatedVisit.setId(visitId);
        }
        visitService.save(updatedVisit);
    }
}
