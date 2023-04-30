package com.agk.agkjpa;

import com.agk.agkjpa.models.Student;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AgkJpaApplicationTests {

	@Test
	void contextLoads() {
	}
/*
	@Autowired
	private JacksonTester<Student> json;

	@Test
	void studentSerializationTests() throws IOException {
		Student student = new Student(1L, "Aswin");
		assertThat(json.write(student)).isStrictlyEqualToJson("expected.json");
		assertThat(json.write(student)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(student)).extractingJsonPathNumberValue("@.id")
				.isEqualTo(1);
		assertThat(json.write(student)).hasJsonPathStringValue("@.name");
		assertThat(json.write(student)).extractingJsonPathStringValue("@.name")
				.isEqualTo("Aswin");
	}

	@Test
	void studentDeSerializationTest() throws IOException {
		String expected = """
				{
					"id":1,
					"name":"Aswin"
				}
				""";

		assertThat(json.parse(expected)).isEqualTo(new Student(1, "Aswin"));

		assertThat(json.parseObject(expected).getId()).isEqualTo(1L);
		assertThat(json.parseObject(expected).getName()).isEqualTo("Aswin");
	}
*/
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnStudentInfoWhenDataIsSaved(){
		ResponseEntity<String> initial_response = restTemplate.getForEntity("/student/1", String.class);
		assertThat(initial_response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

		String name = "Aswin";
		Student student = new Student();
		student.setName(name);
		ResponseEntity<Student> response = restTemplate.postForEntity("/add", student, Student.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Student st = documentContext.read("$");
		assertThat(st.getId()).isEqualTo(1);
		assertThat(st.getName()).isEqualTo(name);

		/*Number id = documentContext.read("$.id");
		assertThat(id).isNotNull();
		assertThat(id).isEqualTo(1);
		String resp_name = documentContext.read("$.name");
		assertThat(resp_name).isEqualTo(name);*/
	}
}