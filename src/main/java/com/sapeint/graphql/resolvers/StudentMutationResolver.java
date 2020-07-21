package com.sapeint.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.sapeint.graphql.modal.Student;
import com.sapeint.graphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class StudentMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private StudentService studentService;


    public CompletableFuture<Student> createStudent(Student student){
        CompletableFuture studentPromise = CompletableFuture.supplyAsync(()->{
           return studentService.createStudentRecord(student);
        });
        return studentPromise;
    }
}
