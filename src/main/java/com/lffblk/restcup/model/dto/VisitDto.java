package com.lffblk.restcup.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 15.08.2017.
 */
public class VisitDto {
    private Integer id = -1;
    @JsonProperty("user") private Integer userId = -1;
    @JsonProperty("location") private Integer locationId = -1;
    @JsonProperty("visited_at") private Long date = -1L;
    private Integer mark = -1;

    public VisitDto() {}

    public VisitDto(Integer id, Integer userId, Integer locationId, Long date, Integer mark) {
        this.id = id;
        this.userId = userId;
        this.locationId = locationId;
        this.date = date;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
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
