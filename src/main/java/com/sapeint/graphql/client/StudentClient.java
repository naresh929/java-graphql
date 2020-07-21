package com.sapeint.graphql.client;

import com.sapeint.graphql.modal.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "studentclient",url = "http://localhost:8082/")
public interface StudentClient {

    @GetMapping(value = "/student/{name}")
    public Student getStudent(@PathVariable String name);

    @PostMapping(value = "/student")
    public Student createStudent(@RequestBody Student student);

    @GetMapping(value = "/student")
    public List<Student> getStudents();
}
