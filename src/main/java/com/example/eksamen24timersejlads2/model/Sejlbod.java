package com.example.eksamen24timersejlads2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sejlbod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int fod;
    private int points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFod() {
        return fod;
    }

    public void setFod(int fod) {
        this.fod = fod;
    }

    public String getBodType() {
        if (fod < 25) {
            return "Mindre end 25 fod";
        } else if (fod >= 25 && fod <= 40) {
            return "Mellem 25-40 fod";
        } else {
            return "Længere end 40 fod";
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

