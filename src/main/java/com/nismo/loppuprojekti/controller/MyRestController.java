package com.nismo.loppuprojekti.controller;

import com.nismo.loppuprojekti.data.Course;
import com.nismo.loppuprojekti.data.Enrollment;
import com.nismo.loppuprojekti.data.Student;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nismo.loppuprojekti.service.CourseService;
import com.nismo.loppuprojekti.service.EnrollmentService;
import com.nismo.loppuprojekti.service.StudentService;

@RestController
public class MyRestController {

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    EnrollmentService enrollmentService;

    @GetMapping("students")
    public List<Student> getStudents() {

        return studentService.getStudents();
    }

    @PostMapping("addstudent")
    public ResponseEntity<Student> addStudent(@RequestParam String fname, String lname) {
        Student s = new Student(fname, lname);
        try {
            studentService.addStudent(s);
            return new ResponseEntity<>(s, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("courses")
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping("addcourse")
    public Course addCourse(@RequestParam String courseName, String teacher, String classroom) {
        Course c = new Course(courseName, teacher, classroom);
        try {
            courseService.addCourse(c);
            return c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("addenrollment")
    public Enrollment addEnrollment(@RequestParam int studentId, int courseId) {
        Enrollment enrol = new Enrollment(studentId, courseId);
        try {
            enrollmentService.addEnrollment(enrol);
            return enrol;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("courses/{courseId}")
    public List<Student> getCourse(@PathVariable int courseId) {
        Course c = courseService.getCourseById(courseId);
        if (c == null) {
            return null;
        }
        return enrollmentService.getStudentsOnCourse(courseService.getCourseById(courseId));
    }

    @GetMapping("students/{id}")
    public List<Course> getStudentbyId(@PathVariable int id) {
        Student s = studentService.getStudentbyId(id);
        if (s == null) {
            return null;
        }
        return enrollmentService.getCoursesByStudent(studentService.getStudentbyId(id));
    }

    @GetMapping("enrollments")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
}
