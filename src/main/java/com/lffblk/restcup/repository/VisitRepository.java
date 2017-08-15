package com.lffblk.restcup.repository;

import com.lffblk.restcup.model.Visit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VisitRepository extends MongoRepository<Visit, Integer> {
    List<Visit> findByUserId(Integer userId);
    List<Visit> findByLocationId(Integer locationId);
}
