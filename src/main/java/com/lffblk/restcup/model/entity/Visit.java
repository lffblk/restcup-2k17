package com.lffblk.restcup.model.entity;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

/**
 * Created by lffblk on 14.08.2017.
 */
public class Visit {
    @Id private int id;
    private int userId;
    private int locationId;
    private long date;
    private int mark;

    public Visit(int id, int userId, int locationId, long date, int mark) {
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
