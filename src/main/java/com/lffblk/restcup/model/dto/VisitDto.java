package com.lffblk.restcup.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 15.08.2017.
 */
public class VisitDto {
    private int id;
    @JsonProperty("user") private int userId;
    @JsonProperty("location") private int locationId;
    @JsonProperty("visited_at") private long date;
    private int mark;

    public VisitDto() {}

    public VisitDto(int id, int userId, int locationId, long date, int mark) {
        this.id = id;
        this.userId = userId;
        this.locationId = locationId;
        this.date = date;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public long getDate() {
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
                .add("id", id)
                .add("userId", userId)
                .add("locationId", locationId)
                .add("date", date)
                .add("mark", mark)
                .toString();
    }
}
