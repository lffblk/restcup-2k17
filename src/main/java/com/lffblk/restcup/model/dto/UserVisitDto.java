package com.lffblk.restcup.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 15.08.2017.
 */
public class UserVisitDto {
    private String place;
    @JsonProperty("visited_at")private long date;
    private int mark;

    public UserVisitDto() {}

    public UserVisitDto(String place, long date, int mark) {
        this.place = place;
        this.date = date;
        this.mark = mark;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("place", place)
                .add("date", date)
                .add("mark", mark)
                .toString();
    }
}
