package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {

    private static final List<Integer> allTasks = Arrays.asList(1, 7, 9, 9, 11, 8, 13, 16, 7, 10, 11, 3, 2, 1, 1);

    private static List<List<String>> readCSV(String filename) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return records;
    }

    public static List<Student> parserFromCSV(String filename) {
        List<List<String>> parsedCSV = readCSV(filename);

        List<Student> students = new ArrayList<>();
        List<String> themes = getThemes(parsedCSV);
        List<String> tasksName = new ArrayList<>(parsedCSV.get(1));

        for (var i : parsedCSV.subList(3, parsedCSV.size())) {
            String[] fio = i.get(0).split(" ");

            var person = new Person(fio[1], fio[0]);
            var courses = new ArrayList<Course>();
            var personTasks = new ArrayList<Theme>();

            int startIndex = 2;
            for (var j = 0; j < themes.size(); j++) {
                var tasks = new ArrayList<Task>();
                var tasksOfTheme = tasksName.subList(startIndex, startIndex + allTasks.get(j));
                int taskValuePosition = startIndex;
                int taskMaxScorePosition = 0;

                for (var taskName : tasksOfTheme) {
                    tasks.add(new Task(taskName, Integer.parseInt(i.get(taskValuePosition)), Integer.parseInt(parsedCSV.get(2).get(startIndex + taskMaxScorePosition))));
                    taskValuePosition++;
                    taskMaxScorePosition++;
                }
                personTasks.add(new Theme(themes.get(j), tasks, tasks.get(0).getScore(), Integer.parseInt(parsedCSV.get(2).get(startIndex))));
                startIndex = startIndex + allTasks.get(j);
            }

            var course = new Course("Java", personTasks, Integer.parseInt(parsedCSV.get(2).get(2)), i.get(1));
            courses.add(course);
            students.add(new Student(person, i.get(1), courses));
        }
        return students;
    }

    private static List<String> getThemes(List<List<String>> parsedCSV) {
        var themes = new ArrayList<String>();
        for (String theme : parsedCSV.get(0)) {
            if (theme.length() > 1)
                themes.add(theme);
        }
        
        return themes;
    }
}
