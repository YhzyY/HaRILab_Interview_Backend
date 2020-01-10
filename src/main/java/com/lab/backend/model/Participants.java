package com.lab.backend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class Participants {
    private String name;
    private String uuid;

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

    public String getName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Participants(){
    }

    public Participants(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Participant: [id=" + id + ", name =" + name + ", uuid="+ uuid + "]";
    }
}
