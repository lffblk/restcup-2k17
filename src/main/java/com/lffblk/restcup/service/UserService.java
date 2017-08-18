package com.lffblk.restcup.service;

import com.lffblk.restcup.controller.UserController;
import com.lffblk.restcup.exception.EntityNotFoundException;
import com.lffblk.restcup.model.entity.User;
import com.lffblk.restcup.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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
        Calendar calendar = Calendar.getInstance();

        if (fromAge != null) {
            calendar.set(Calendar.YEAR, fromAge);
            int daysInYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
            long yearsInMillis = TimeUnit.DAYS.toMillis(daysInYear);
            long relativeFromDate = System.currentTimeMillis() - yearsInMillis;
            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.set(Calendar.SECOND, user.getBirthDate());
            Calendar relativeCalendar = Calendar.getInstance();
            relativeCalendar.setTimeInMillis(relativeFromDate);
            if (birthCalendar.compareTo(relativeCalendar) < 0) return  null;
//            long birthDayInMillis = TimeUnit.SECONDS.toMillis(user.getBirthDate());
        }

        if (toAge != null) {
            calendar.set(Calendar.YEAR, toAge);
            int daysInYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
            long yearsInMillis = TimeUnit.DAYS.toMillis(daysInYear);
            long relativeToDate = System.currentTimeMillis() - yearsInMillis;
            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTimeInMillis(user.getBirthDate());
            Calendar relativeCalendar = Calendar.getInstance();
            relativeCalendar.setTimeInMillis(relativeToDate);
            if (birthCalendar.compareTo(relativeCalendar) > 0) return  null;
        }
        return user;
    }

}
