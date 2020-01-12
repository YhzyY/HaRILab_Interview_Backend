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
    private String uuid;
    private LocalDate userDate;

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

    public void setAttackDate(LocalDate attackDate) {
        this.attackDate = attackDate;
    }

    public LocalTime getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(LocalTime attackTime) {
        this.attackTime = attackTime;
    }

    public String getAttackLocation() {
        return attackLocation;
    }

    public void setAttackLocation(String attackLocation) {
        this.attackLocation = attackLocation;
    }

    public String getuuid() {
        return uuid;
    }

    public void setuuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDate getUserDate() {
        return userDate;
    }

    public void setUserDate(LocalDate userDate) {
        this.userDate = userDate;
    }

    public Attacks(){
    }

    public Attacks(LocalDate attackDate, LocalTime attackTime, String attackLocation, String uuid, LocalDate userDate) {
        this.attackDate = attackDate;
        this.attackTime = attackTime;
        this.attackLocation = attackLocation;
        this.uuid = uuid;
        this.userDate = userDate;
    }

    @Override
    public String toString() {
        return "Attack: [id=" + id + ", uuid="+ uuid + ", attackData =" + attackDate + ", attackTime =" + attackTime +" , attackLocation = " + attackLocation + "]";
    }
}
