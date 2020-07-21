package com.sapeint.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.sapeint.graphql.modal.Student;
import com.sapeint.graphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class StudentQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private StudentService studentService;

    public CompletableFuture<Student> student(String name){
        CompletableFuture studentPromise =CompletableFuture.supplyAsync(()->{
            Student student = studentService.getStudentDetailsByName(name);
            if (student!=null){
                return student;
            }
            return null;
        });
        return studentPromise;
    }

    public CompletableFuture<List<Student>> students(){
        CompletableFuture studentsPromise =CompletableFuture.supplyAsync(()->{
            List<Student> students = studentService.getStudents();
            if (students!=null){
                return students;
            }
            return null;
        });
        return studentsPromise;
    }
}
