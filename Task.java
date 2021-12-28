package com.company;

public class Task {
    private final String name;
    private final int score;
    private final int maxScore;

    public Task(String name, int score,int maxScore) {
        this.name = name;
        this.score = score;
        this.maxScore = maxScore;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Задание: " + name+" - "+score;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
