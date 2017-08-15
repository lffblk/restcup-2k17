package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.User;
import com.lffblk.restcup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public User getUser(@PathVariable Integer userId) {
//        return new User(userId, "Vasya", "Pupkin", "m", -73267200, "ygothepewoegidno@me.com");
        return userRepository.findOne(userId);
    }
}
