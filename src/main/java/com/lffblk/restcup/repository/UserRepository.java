package com.lffblk.restcup.repository;

import com.lffblk.restcup.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {}
