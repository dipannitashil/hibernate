package com.dipannita.hibernatedemo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.dipannita.hibernatedemo.entity.Course;
// can specify from where to load context
@SpringBootTest // (classes=HibernatedemoApplication.class)
public class CourseRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository cr; 
	@Test
	void findById_test() {
		Course course = cr.findById(10001);
		assertEquals("English", course.getName());
	}
	
	@Test
	@DirtiesContext // reset data for other tests
	void deleteById_test() {
		cr.deleteById(10001);
		
		assertNull(cr.findById(10001));
	}
	
	@Test
	@DirtiesContext // reset data for other tests
	void save_test() {
		Course course = cr.save(new Course("Hindi"));
		
		assertNotNull(course.getId());
	}
	
	@Test
	@DirtiesContext // reset data for other tests
	void save_testUpdate() {
		Course course = cr.findById(10001);
		course.setName("English Advannced");
		Course courseUpdated = cr.save(course);
		
		assertEquals("English Advannced", courseUpdated.getName());
	}

}
