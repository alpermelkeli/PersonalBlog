package com.alpermelkeli.personalblog.uı.skills.view;

public class Corner {
    private int cornerIndex;
    private double progress;
    private String skillName;

    public Corner(int cornerIndex, double progress, String skillName) {
        this.cornerIndex = cornerIndex;
        this.progress = progress;
        this.skillName = skillName;
    }

    public int getCornerIndex() {
        return cornerIndex;
    }

    public void setCornerIndex(int cornerIndex) {
        this.cornerIndex = cornerIndex;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
