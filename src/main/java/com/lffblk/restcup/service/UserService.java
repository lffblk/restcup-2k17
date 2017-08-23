package com.lffblk.restcup.service;

import com.lffblk.restcup.exception.EntityNotFoundException;
import com.lffblk.restcup.model.entity.User;
import com.lffblk.restcup.repository.UserRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lffblk on 15.08.2017.
 */
@Service
public class UserService {

    private final static Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer id) {
        return getUser(id);
    }

    public User getUser(Integer id, Integer fromAge, Integer toAge) {
        LOG.debug("getUser: id = {}, fromAge = {}, toAge = {}", id, fromAge, toAge);
        User user = getUser(id);
        User checkedUser = checkUserBirthDate(user, fromAge, toAge);
        LOG.debug("checkedUser = {}", checkedUser);
        return checkedUser;
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

    private User checkUserBirthDate(User user, Integer fromAge, Integer toAge) {
        LOG.debug("checkUserFrom: User = {}, fromAge = {}, toAge = {}", user, fromAge, toAge);
        if (fromAge == null && toAge == null) return user;
        DateTime now = new DateTime(System.currentTimeMillis());
        DateTime birthDate = new DateTime(user.getBirthDate() * 1000L);

        if (fromAge != null) {
            DateTime relatedDate = new DateTime(now).minusYears(fromAge);
            if (birthDate.isAfter(relatedDate)) return null;
        }

        if (toAge != null) {
            DateTime relatedDate = new DateTime(now).minusYears(toAge);
            if (birthDate.isBefore(relatedDate)) return null;
        }
        return user;
    }

}
