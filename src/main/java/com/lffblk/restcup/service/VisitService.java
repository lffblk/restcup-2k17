package com.lffblk.restcup.service;

import com.lffblk.restcup.exception.EntityNotFoundException;
import com.lffblk.restcup.model.entity.Visit;
import com.lffblk.restcup.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lffblk on 15.08.2017.
 */
@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    public Visit getVisitById(Integer id) {
        Visit visit = visitRepository.findOne(id);
        if (visit == null) {
            throw new EntityNotFoundException("Visit with id " + id + " was not found");
        }
        return visit;
    }

    public List<Visit> getVisitsByUserIdAndDates(Integer userId, Long fromDate, Long toDate) {
        if (fromDate == null && toDate == null) {
            return visitRepository.findByUserId(userId);
        }
        else if (fromDate == null) {
            return visitRepository.findByUserIdAndDateBefore(userId, toDate);
        }
        else if (toDate == null) {
            return visitRepository.findByUserIdAndDateAfter(userId, fromDate);
        }
        else {
            return visitRepository.findByUserIdAndDateBetween(userId, fromDate, toDate);
        }
    }

    public List<Visit> getVisits(Integer locationId, Long fromDate, Long toDate) {
        if (fromDate == null && toDate == null) {
            return visitRepository.findByLocationId(locationId);
        }
        else if (fromDate == null) {
            return visitRepository.findByLocationIdAndDateBefore(locationId, toDate);
        }
        else if (toDate == null) {
            return visitRepository.findByLocationIdAndDateAfter(locationId, fromDate);
        }
        else {
            return visitRepository.findByLocationIdAndDateBetween(locationId, fromDate, toDate);
        }
    }

    public void save(Visit visit) {
        visitRepository.save(visit);
    }
}
