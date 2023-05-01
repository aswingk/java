package com.agk.multipledatabase.Product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name, desc;

    public Product(String name, String desc){
        setName(name);
        setDesc(desc);
    }
}
