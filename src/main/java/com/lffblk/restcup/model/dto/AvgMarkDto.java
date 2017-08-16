package com.lffblk.restcup.model.dto;

import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 15.08.2017.
 */
public class AvgMarkDto {
    private double avg;

    public AvgMarkDto() {}

    public AvgMarkDto(double avg) {
        this.avg = avg;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("avg", avg)
                .toString();
    }
}
