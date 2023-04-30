package com.agk.agkjpa;

import com.agk.agkjpa.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentApiControllerTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testGet(){
        String studentsDataUrl = "/students";
        ResponseEntity<CollectionModel> allStudentsResponse =
                testRestTemplate.getForEntity(studentsDataUrl, CollectionModel.class);
        CollectionModel<EntityModel<Student>> data = ((CollectionModel<EntityModel<Student>>)allStudentsResponse.getBody());
        assertThat(data.getContent().size()).isEqualTo(0);

        Student student = new Student("Aswin");
        ResponseEntity<Student> studentResponse
                = testRestTemplate.postForEntity("/add", student, Student.class);

        assertThat(studentResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        allStudentsResponse = testRestTemplate.getForEntity(studentsDataUrl, CollectionModel.class);
        assertThat(allStudentsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
