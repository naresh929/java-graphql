package com.sapeint.graphql.service;

import com.sapeint.graphql.modal.Student;

import java.util.List;

public interface StudentService {

    Student getStudentDetailsByName(String name);
    Student createStudentRecord(Student student);
    List<Student> getStudents();
}
