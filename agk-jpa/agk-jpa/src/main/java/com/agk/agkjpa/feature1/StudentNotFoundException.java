package com.agk.agkjpa.feature1;

public class StudentNotFoundException extends RuntimeException {
    StudentNotFoundException(long id){
        super("Student Not Found with Id: " + id);
    }
    StudentNotFoundException(String name){
        super("Student Not Found with Name: " + name);
    }
}