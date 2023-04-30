package com.agk.agkjpa.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Student")
/*public record Student(@Id
                      @GeneratedValue(strategy = GenerationType.AUTO)
                      Long id,
                      @Column
                      @DefaultValue("")
                      String name) {
}*/

public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    Long id;

    String name;

    public Student(){}

    public Student(String name){
        this.name = name;
    }

    public Student(long id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Student && Objects.equals(id, ((Student) obj).getId()) && name.equals(((Student)obj).getName());
    }

    @Override
    public String toString() {
        return "Student [id = " + id + ", name = " + name +"]";
    }
}