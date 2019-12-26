package com.lab.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Clinicians {
    private String firstName;
    private String lastName;
    private Integer password;

    @Id
    @GeneratedValue

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName() {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName() {
        this.lastName = lastName;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }

    public Clinicians(){
    }

    public Clinicians(String firstName, String lastName, Integer password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Clinician: [id=" + id + ", name =" + firstName + " " + lastName + ", password="+ password + "]";
    }
}
