package com.alpermelkeli.personalblog.model;

public class Skill extends Model{

    String name;
    double progress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
