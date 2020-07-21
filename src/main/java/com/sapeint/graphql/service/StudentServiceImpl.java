package com.sapeint.graphql.service;

import com.sapeint.graphql.client.StudentClient;
import com.sapeint.graphql.modal.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String,String,Student> hashOperations;

//    @Autowired
//    private StudentClient cleint;

    private Map<String,Student> map = new HashMap<>();

    @Autowired
    public StudentServiceImpl(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations =redisTemplate.opsForHash();
    }

    @Override
    public Student getStudentDetailsByName(String name) {
        Student student = null;
        if(null!=name) {
            student = hashOperations.get("Student",name);
            if(student==null){
                student = map.get(name);
                if(student==null){
                    return null;
                }else{
                    hashOperations.put("Student",student.getName(),student);
                }
            }
        }
        return student;
    }
    @Override
    public Student createStudentRecord(Student student) {
        if(null!=student){
            hashOperations.put("Student",student.getName(),student);
            //cleint.createStudent(student);
            map.put(student.getName(),student);
        }
        return student;
    }

    @Override
    public List<Student> getStudents(){
        return map.values().stream().collect(Collectors.toList());
    }
}
