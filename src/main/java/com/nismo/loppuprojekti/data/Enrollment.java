package com.nismo.loppuprojekti.data;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;

    private static int count;

    public Enrollment(int studentId, int courseId){
        this.enrollmentId = count++;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getEnrollmentId(){
        return this.enrollmentId;
    }

    public int getStudentId(){
        return this.studentId;
    }

    public int getCourseId(){
        return this.courseId;
    }
}
