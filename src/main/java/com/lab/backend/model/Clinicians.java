package com.lab.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Clinicians {
    private String userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Clinicians(){
    }

    public Clinicians(String userName, Integer password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Clinician: [id=" + id + ", userName =" + userName + ", password="+ password + "]";
    }
}
