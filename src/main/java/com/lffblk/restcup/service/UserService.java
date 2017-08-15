package com.lffblk.restcup.service;

import com.lffblk.restcup.exception.EntityNotFoundException;
import com.lffblk.restcup.model.entity.User;
import com.lffblk.restcup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lffblk on 15.08.2017.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer id) {
        return getUser(id);
    }

    public User getUser(Integer id, Long fromAge, Long toAge) {
        User user = getUser(id);
        long age = System.currentTimeMillis() - user.getBirthDate();
        if (fromAge == null && toAge == null) {
            return user;
        }
        else if (fromAge == null) {
            return age < toAge ? user : null;
        }
        else if (toAge == null) {
            return age > fromAge ? user : null;
        }
        else {
            return age < toAge && age > fromAge ? user : null;
        }
    }

    private User getUser(Integer id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new EntityNotFoundException("User with id " + id + " was not found");
        }
        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
