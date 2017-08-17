package com.lffblk.restcup.model.dto;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Created by lffblk on 17.08.2017.
 */
public class UsersDto {

    public UsersDto() {}

    private List<UserDto> users;

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("users", users)
                .toString();
    }
}
