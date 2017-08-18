package com.lffblk.restcup.service;

import com.lffblk.restcup.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by lffblk on 19.08.2017.
 */
@Service
public class IdConverterService {
    public Integer convertId(String idStr) {
        Integer id;
        try {
            return Integer.parseInt(idStr);
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }
}
