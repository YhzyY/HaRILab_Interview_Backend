package com.lab.backend.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Attacks {
    private String attackTime;
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

    public String getAttackTime() {
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

    public Attacks(String attackTime, String attackLocation, Integer uuid) {
        this.attackTime = attackTime;
        this.attackLocation = attackLocation;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Attack: [id=" + id + ", uuid="+ uuid + ", attackTime =" + attackTime +" , attackLocation = " + attackLocation + "]";
    }
}
