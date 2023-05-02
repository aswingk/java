package com.agk.jdbcsqlserver.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "emp")
public class Employee {
    @Id
    @GeneratedValue
    Long id;

    @Setter
    @Getter
    private String name, designation;

    @Setter
    @Getter
    int age, experience, salary;

    public Employee(String name, String designation, int age, int experience, int salary){
        setName(name);
        setDesignation(designation);
        setAge(age);
        setExperience(experience);
         setSalary(salary);
    }

    @Override
    public String toString() {
        return "Employee : [Id: " + id +"] Name: " + name + "], [Designation:" + designation + "], " +
                "[Age:" + age + "], [Experience: "+ experience +"], [Salary: " + salary + "]";
    }
}
