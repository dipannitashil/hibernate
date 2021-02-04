package com.dipannita.hibernatedemo.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseRepositoryEMTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository cr;

	@Test
	void play() {
		cr.playWithEntityManager();
	}

}
