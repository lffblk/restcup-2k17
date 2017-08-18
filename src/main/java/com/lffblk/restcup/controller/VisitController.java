package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.dto.EmptyJsonResponse;
import com.lffblk.restcup.model.dto.VisitDto;
import com.lffblk.restcup.model.entity.Visit;
import com.lffblk.restcup.service.IdConverterService;
import com.lffblk.restcup.service.LocationService;
import com.lffblk.restcup.service.PersistenceService;
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
    private ModelMapper modelMapper;

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    IdConverterService idConverterService;

    @RequestMapping(method = RequestMethod.GET, value = "/{visitId}")
    public VisitDto getVisit(@PathVariable String visitId) {
        Integer id = idConverterService.convertId(visitId);
        return modelMapper.map(visitService.getVisitById(id), VisitDto.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{visitId}")
    public ResponseEntity<?> edit(@PathVariable String visitId, @RequestBody VisitDto visitDto) {
        Integer id = idConverterService.convertId(visitId);
        // in case location is absent, 404 error will be thrown
        visitService.getVisitById(id);
        try {
            persistenceService.save(id, visitDto);
            return ResponseEntity.status(HttpStatus.OK).body(new EmptyJsonResponse());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public ResponseEntity<?> add(@RequestBody VisitDto visitDto) {
        try {
            persistenceService.save(null, visitDto);
            return ResponseEntity.status(HttpStatus.OK).body(new EmptyJsonResponse());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
