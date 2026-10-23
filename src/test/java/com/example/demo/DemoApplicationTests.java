package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = org.example.Main.class, properties = {
        "spring.datasource.url=${TEST_DB_URL:jdbc:postgresql://localhost:5432/survey_lab3_test}"
})
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
