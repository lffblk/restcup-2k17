package com.lffblk.restcup.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 15.08.2017.
 */
public class UserDto {
    private Integer id = -1;
    @JsonProperty("first_name") private String firstName = "";
    @JsonProperty("last_name")private String lastName = "";
    private String gender = "";
    @JsonProperty("birth_date") private Integer birthDate = -1;
    private String email = "";

    public UserDto() {}

    public UserDto(Integer id, String firstName, String lastName, String gender, Integer birthDate, String email) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Integer birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("gender", gender)
                .add("birthDate", birthDate)
                .add("email", email)
                .toString();
    }
}
