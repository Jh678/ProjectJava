package com.company;

import java.util.List;

public class Course {
    private final String name;
    private final List<Theme> themes;
    private int maxScore;
    private final String group;

    public Course(String name, List<Theme> themes, int maxMark, String group) {
        this.name = name;
        this.themes = themes;
        this.maxScore = maxMark;
        this.group = group;

    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for(var theme : themes)
            builder.append('\t').append(theme.toString());
        return "Курс: " + name +" Группа: "+group +" Масимум баллов: " + maxScore
        +" Темы: \n"+builder.toString();

    }

    public String getName() {
        return name;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getGroup() {
        return group;
    }

    public List<Theme> getThemes() {
        return themes;
    }
}
