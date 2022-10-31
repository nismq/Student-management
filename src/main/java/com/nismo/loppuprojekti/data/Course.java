package com.nismo.loppuprojekti.data;

public class Course {
    private int courseId;
    private String courseName;
    private String classroom;
    private String teacher;

    private static int count; 

    public Course(String courseName, String teacher, String classroom){
        this.courseName = courseName;
        this.teacher = teacher;
        this.classroom = classroom;
        this.courseId = count++;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public int getCourseId(){
        return this.courseId;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public void setClassroom(String classroom){
        this.classroom = classroom;
    }

    public String getClassroom(){
        return this.classroom;
    }

    public String getTeacher(){
        return this.teacher;
    }

    public void setTeacher(String teacher){
        this.teacher = teacher;
    }
}
