package com.lffblk.restcup.repository;

import com.lffblk.restcup.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, Integer> {}
