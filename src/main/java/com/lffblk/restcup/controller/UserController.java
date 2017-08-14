package com.lffblk.restcup.controller;

import com.lffblk.restcup.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lffblk on 14.08.2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public User getUser(@PathVariable String userId) {
        return new User(Integer.parseInt(userId), "Vasya", "Pupkin", "m", -73267200,
                "ygothepewoegidno@me.com");
    }
}
