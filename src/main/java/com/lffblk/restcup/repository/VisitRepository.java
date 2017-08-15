package com.lffblk.restcup.repository;

import com.lffblk.restcup.model.entity.Visit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VisitRepository extends MongoRepository<Visit, Integer> {
    List<Visit> findByUserId(Integer userId);
    List<Visit> findByUserIdAndDateAfter(Integer userId, long date);
    List<Visit> findByUserIdAndDateBefore(Integer userId, long date);
    List<Visit> findByUserIdAndDateBetween(Integer userId, long dateAfter, long dateBefore);
    List<Visit> findByLocationId(Integer locationId);
    List<Visit> findByLocationIdAndDateAfter(Integer locationId, long date);
    List<Visit> findByLocationIdAndDateBefore(Integer locationId, long date);
    List<Visit> findByLocationIdAndDateBetween(Integer locationId, long dateAfter, long dateBefore);
}
