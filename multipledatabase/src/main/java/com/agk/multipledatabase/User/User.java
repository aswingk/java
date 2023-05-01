package com.agk.multipledatabase.User;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name, role;

    public User(String name, String role){
        setName(name);
        setRole(role);
    }
}