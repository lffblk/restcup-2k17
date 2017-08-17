package com.lffblk.restcup.model.dto;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Created by lffblk on 17.08.2017.
 */
public class VisitsDto {

    public VisitsDto() {}

    private List<VisitDto> visits;

    public List<VisitDto> getVisits() {
        return visits;
    }

    public void setVisits(List<VisitDto> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("visits", visits)
                .toString();
    }
}
