package com.lffblk.restcup.model.entity;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by lffblk on 14.08.2017.
 */
public class Visit {
    @NotNull @Id private Integer id;
    @NotNull private Integer userId;
    @NotNull private Integer locationId;
    @NotNull private Long date;
    @NotNull private Integer mark;

    public Visit() {}

    public Visit(Integer id, Integer userId, Integer locationId, Long date, Integer mark) {
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
