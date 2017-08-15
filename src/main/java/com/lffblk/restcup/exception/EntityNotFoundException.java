package com.lffblk.restcup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lffblk on 15.08.2017.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such entity")
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
