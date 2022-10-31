package com.nismo.loppuprojekti.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nismo.loppuprojekti.data.Course;
import com.nismo.loppuprojekti.data.Enrollment;
import com.nismo.loppuprojekti.data.Student;

import java.util.List;

@Service
public class EnrollmentService {
    List<Enrollment> enrollments = new ArrayList<>();

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    FileService fileService;

    public EnrollmentService() {
        fileService = new FileService();

        try {
            enrollments = fileService.readEnrollmentsFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEnrollment(Enrollment newEnrollment) throws Exception {
        if (studentService.getStudentbyId(newEnrollment.getStudentId()) == null
                || courseService.getCourseById(newEnrollment.getCourseId()) == null) {
            throw new Exception("Student or course doesn't exist.");
        }

        for (Enrollment enrollment : enrollments) {
            if (newEnrollment.getStudentId() == enrollment.getStudentId()
                    && newEnrollment.getCourseId() == enrollment.getCourseId()) {
                throw new Exception("Enrollment already exists.");
            }
        }

        enrollments.add(newEnrollment);
        try {
            fileService.writeEnrollmentsToFile(enrollments);
        } catch (Exception e) {
            System.out.println("Writing to file failed");
        }
    }

    public Enrollment getEnrollmentById(int enrollmentId) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getEnrollmentId() == enrollmentId) {
                return enrollment;
            }
        }
        return null;
    }

    public List<Student> getStudentsOnCourse(Course course) {
        List<Student> students = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId() == course.getCourseId()) {
                students.add(studentService.getStudentbyId(enrollment.getStudentId()));
            }
        }
        if (students.size() == 0) {
            return null;
        }

        return new ArrayList<>(students);
    }

    public List<Course> getCoursesByStudent(Student student) {
        List<Course> courses = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == student.getStudentId()) {
                courses.add(courseService.getCourseById(enrollment.getCourseId()));
            }
        }

        return new ArrayList<>(courses);
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

}
