package com.lab.backend.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Attacks {
    private LocalDate attackDate;
    private LocalTime attackTime;

    private String attackLocation;
    private Integer uuid;

    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getAttackDate() {
        return attackDate;
    }

    public void setAttackDate() {
        this.attackDate = attackDate;
    }

    public LocalTime getAttackTime() {
        return attackTime;
    }

    public void setAttackTime() {
        this.attackTime = attackTime;
    }

    public String getAttackLocation() {
        return attackLocation;
    }

    public void setAttackLocation() {
        this.attackLocation = attackLocation;
    }

    public Integer getuuid() {
        return uuid;
    }

    public void setuuid() {
        this.uuid = uuid;
    }

    public Attacks(){
    }

    public Attacks(LocalDate attackDate, LocalTime attackTime, String attackLocation, Integer uuid) {
        this.attackDate = attackDate;
        this.attackTime = attackTime;
        this.attackLocation = attackLocation;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Attack: [id=" + id + ", uuid="+ uuid + ", attackData =" + attackDate + ", attackTime =" + attackTime +" , attackLocation = " + attackLocation + "]";
    }
}
