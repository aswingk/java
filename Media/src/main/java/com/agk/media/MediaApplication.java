package com.agk.media;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MediaApplication implements CommandLineRunner {

    public static void main(String[] args){
        System.exit(SpringApplication.exit(SpringApplication.run(MediaApplication.class, args)));
    }
//    @Autowired
//    OrderRepository repository;

    @Override
    public void run(String... args) throws Exception {
        for(int i=0;i<100;i++){
             // repository.save(new Order("title " + i, Status.IN_PROGRESS));
        }
    }
}