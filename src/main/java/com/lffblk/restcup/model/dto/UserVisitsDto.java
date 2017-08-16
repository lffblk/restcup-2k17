package com.lffblk.restcup.model.dto;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Created by lffblk on 15.08.2017.
 */
public class UserVisitsDto {
    private List<UserVisitDto> visits;

    public UserVisitsDto() {}

    public UserVisitsDto(List<UserVisitDto> visits) {
        this.visits = visits;
    }

    public List<UserVisitDto> getVisits() {
        return visits;
    }

    public void setVisits(List<UserVisitDto> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("visits", visits)
                .toString();
    }
}
