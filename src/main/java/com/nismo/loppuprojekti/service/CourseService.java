package com.nismo.loppuprojekti.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.nismo.loppuprojekti.data.Course;

@Service
public class CourseService {

    @Autowired
    FileService fileService;
    private List<Course> courses = new ArrayList<>();

    public CourseService(){
        //Without this line try block gave NullPointerException
        fileService = new FileService();

        try {
            courses = fileService.readCoursesFromFile();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Course> getCourses(){
        return new ArrayList<>(courses);
    }

    public Course getCourseById(int courseId){
        for (Course course : courses) {
            if(course.getCourseId() == courseId){
                return course;
            }
        }

        return null;
    }

    public void addCourse(Course course)throws Exception{
        if(course.getCourseName() == ""){
            throw new Exception("Enter course name");
        }
        courses.add(course);
        
        try {
            fileService.writeCoursesToFile(courses);
            
        } catch (Exception e) {
            System.out.println("Writing to file failed");
        }
    }
}
