package com.company;

import java.util.List;

public class Theme {
    private final String name;
    private List<Task> tasks;
    private final int studentMaxPoint;
    private final int maxPoint;
    public Theme(String name, List<Task> tasks, int studentMaxPoint, int maxPoint) {
        this.name = name;
        this.tasks = tasks;
        this.studentMaxPoint = studentMaxPoint;
        this.maxPoint = maxPoint;
    }

    @Override
    public String toString() {
        return "Тема: " + name+" Максимум баллов: "+maxPoint + " Текущий балл: " + studentMaxPoint;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    public int getStudentMaxPoint() {
        return studentMaxPoint;
    }

    public int getMaxPoint() {
        return maxPoint;
    }
}
