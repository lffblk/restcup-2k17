package com.lffblk.restcup.repository;

import com.lffblk.restcup.model.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocationRepository extends MongoRepository<Location, Integer> {
    Location findByIdAndCountry(Integer id, String country);
    Location findByIdAndDistanceBefore(Integer id, Integer distance);
    Location findByIdAndCountryAndDistanceBefore(Integer id, String country, Integer distance);
}
