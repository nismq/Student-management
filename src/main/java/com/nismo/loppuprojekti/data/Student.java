package com.nismo.loppuprojekti.data;

public class Student {
    private String fname;
    private String lname;
    private int studentId;

    private static int count;

    public Student(){
        this(null, null);
    }
    
    public Student(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
        this.studentId = count++;
    }

    public String getName(){
        if(this.fname == "" || this.lname == ""){
            return null;
        }

        return this.fname + " " + this.lname;
    }

    public int getStudentId(){
        return this.studentId;
    }

    public void setFirstName(String fname){
        this.fname = fname;
    }

    public void setLastName(String lname){
        this.lname = lname;
    }

    public void setName(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
    }
}
