package com.agk.jdbcsqlserver;

import com.agk.jdbcsqlserver.models.Employee;
import com.agk.jdbcsqlserver.models.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class JdbcSqlServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JdbcSqlServerApplication.class, args);
    }

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {

        /*

        for(int i=0;i<10;i++){
            employeeRepo.save(
                    new Employee("Aswin " + i,
                            "Senior S/w Emp " + i,
                            30+i/2,
                            11, 5000));
        }
        List<Employee> emps =  employeeRepo.namebyage("Aswin 3");
        emps.forEach(System.out::println);

        */

        System.out.println("Full List : ");

        BeanPropertyRowMapper<Employee> rowMapper = BeanPropertyRowMapper.newInstance(Employee.class);

        String sql = "Select * from emp"; // table in sql server is emp
        List<Employee> list = jdbcTemplate.query(sql, rowMapper);
        list.forEach(System.out::println);
        System.out.println("Done");
    }
}