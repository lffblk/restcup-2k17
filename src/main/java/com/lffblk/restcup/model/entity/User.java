package com.lffblk.restcup.model.entity;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by lffblk on 14.08.2017.
 */

public class User {
    @NotNull @Id private Integer id;
    @NotNull private String firstName;
    @NotNull private String lastName;
    @NotNull private String gender;
    @NotNull private Integer birthDate;
    @NotNull private String email;

    public User() {}

    public User(Integer id, String firstName, String lastName, String gender, Integer birthDate, String email) {
        this.id = id;
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
