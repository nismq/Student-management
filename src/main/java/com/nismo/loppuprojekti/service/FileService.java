package com.nismo.loppuprojekti.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import com.nismo.loppuprojekti.data.Course;
import com.nismo.loppuprojekti.data.Enrollment;
import com.nismo.loppuprojekti.data.Student;

@Service
public class FileService {

    public void writeStudentsToFile(List<Student> students) throws IOException {
        FileWriter fw = new FileWriter(new File("students.txt"));

        for (Student student : students) {
            fw.write(student.getName() + System.lineSeparator());
        }

        fw.close();
    }

    public List<Student> readStudentsFromFile() throws FileNotFoundException {
        List<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(new File("students.txt"));
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] name = input.split(" ");
            students.add(new Student(name[0], name[1]));
        }

        sc.close();
        return students;
    }

    public void writeCoursesToFile(List<Course> courses) throws IOException {
        FileWriter fw = new FileWriter(new File("courses.txt"));

        for (Course course : courses) {
            fw.write(course.getCourseName() + "," + course.getTeacher() + "," + course.getClassroom()
                    + System.lineSeparator());
        }

        fw.close();
    }

    public List<Course> readCoursesFromFile() throws FileNotFoundException {
        List<Course> courses = new ArrayList<>();
        Scanner sc = new Scanner(new File("courses.txt"));
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] fields = input.split(",");
            courses.add(new Course(fields[0], fields[1], fields[2]));
        }
        sc.close();
        return new ArrayList<>(courses);
    }

    public void writeEnrollmentsToFile(List<Enrollment> enrollments) throws IOException {
        FileWriter fw = new FileWriter(new File("enrollments.txt"));

        for (Enrollment enrollment : enrollments) {
            fw.write(enrollment.getStudentId() + "," + enrollment.getCourseId() + System.lineSeparator());
        }

        fw.close();
    }

    public List<Enrollment> readEnrollmentsFromFile() throws FileNotFoundException {
        List<Enrollment> enrollments = new ArrayList<>();
        Scanner sc = new Scanner(new File("enrollments.txt"));
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] fields = input.split(",");
            enrollments.add(new Enrollment(Integer.parseInt(fields[0]), Integer.parseInt(fields[1])));
        }

        sc.close();
        return new ArrayList<>(enrollments);
    }
}
