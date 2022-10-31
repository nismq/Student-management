package com.nismo.loppuprojekti.service;

import com.nismo.loppuprojekti.data.Student;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    FileService fileService;

    private List<Student> students = new ArrayList<>();

    public StudentService() {
        // Without this line try block gave NullPointerException
        fileService = new FileService();

        try {
            students = fileService.readStudentsFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) throws Exception {
        if (student.getName() == null) {
            throw new Exception("Student needs name.");
        }
        students.add(student);

        try {
            fileService.writeStudentsToFile(students);
        } catch (Exception e) {
            System.out.println("Writing to file failed");
        }
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentbyId(int id) {

        for (Student student : students) {
            if (student.getStudentId() == id) {
                return student;
            }
        }

        return null;
    }
}
