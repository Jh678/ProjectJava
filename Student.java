package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends Person {
    private List<Course> courses;

    public Student(String name, String surname) {
        super(name, surname);
        courses = new ArrayList<>();
    }

    public Student(Person p, String group, List<Course> courses) {
        super(p.getName(), p.getSurname());
        this.courses = courses;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for(var course : courses)
            builder.append('\t').append(course.toString());
        return super.toString() +'\n' + builder.toString();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course courses) {
        this.courses.add(courses);
    }

    public void addSubjects(List<Course> courses) {
        this.courses.addAll(courses);
    }


}
