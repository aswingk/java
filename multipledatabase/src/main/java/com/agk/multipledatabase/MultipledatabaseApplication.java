package com.agk.multipledatabase;

import com.agk.multipledatabase.Product.Product;
import com.agk.multipledatabase.Product.ProductRepository;
import com.agk.multipledatabase.User.User;
import com.agk.multipledatabase.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipledatabaseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultipledatabaseApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                productRepository.save(new Product("Product " + i, "Desc " + i));
                userRepository.save(new User("Aswin " + i, "Role " + i));
            }
        }).start();
    }
}
