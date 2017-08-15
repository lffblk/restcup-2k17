package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.Visit;
import com.lffblk.restcup.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitRepository visitRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{visitId}")
    public Visit getVisit(@PathVariable Integer visitId) {
//        return new Visit(visitId, 148, 144, 1002632924, 4);
        return visitRepository.findOne(visitId);
    }
}
