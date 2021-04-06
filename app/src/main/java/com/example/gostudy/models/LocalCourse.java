package com.example.gostudy.models;

public class LocalCourse {
    private String name;
    private float credits;

    public LocalCourse(String name, float credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public float getCredits() {
        return credits;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }
}
