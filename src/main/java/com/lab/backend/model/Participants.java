package com.lab.backend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Participants {
    private String firstName;
    private String lastName;
    private Integer uuid;

    @Id
    @GeneratedValue
//    @GeneratedValue(generator = "paymentableGenerator")
//    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
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

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = uuid;
    }

    public Participants(){
    }

    public Participants(String firstName, String lastName, Integer uuid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Participant: [id=" + id + ", name =" + firstName + " " + lastName + ", uuid="+ uuid + "]";
    }
}
