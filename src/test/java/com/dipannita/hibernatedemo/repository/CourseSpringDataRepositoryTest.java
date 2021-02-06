package com.dipannita.hibernatedemo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import com.dipannita.hibernatedemo.entity.Course;

@SpringBootTest
public class CourseSpringDataRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseSpringDataRepository cr;
	
	@Test
	public void find() {
		Optional<Course> courseOptional = cr.findById(10001l);
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	@DirtiesContext
	public void testSpringDataMethods() {
		// count
		// delete
		// exists
		// find
		// flush, saveAndFlush
		// getOne
		// save
		Course entity = new Course("Project Management");
		cr.save(entity);
		assertNotNull(entity.getId());
	}
	
	@Test
	public void sort() {
		Sort sort = Sort.by(Sort.Direction.ASC, "name").and(Sort.by(Sort.Direction.ASC, "id"));
		logger.info("All courses sorted by name and id-> {}", cr.findAll(sort));
	}
	
	@Test
	@DirtiesContext
	public void pagination() {
		cr.save(new Course("dummy1"));
		cr.save(new Course("dummy2"));
		cr.save(new Course("dummy3"));
		cr.save(new Course("dummy4"));
		cr.save(new Course("dummy5"));
		cr.save(new Course("dummy6"));
		cr.save(new Course("dummy7"));
		cr.save(new Course("dummy8"));
		cr.save(new Course("dummy9"));
		cr.save(new Course("dummy0"));
		
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = cr.findAll(pageRequest);
		logger.info("Page first-> {}", firstPage.getContent());
		
		Pageable secondPage = firstPage.nextPageable();
		Page<Course> second = cr.findAll(secondPage);
		logger.info("Page second-> {}", second.getContent());
		assertEquals(0,firstPage.getNumber());
		assertEquals(1,second.getNumber());
	}
	
	@Test
	public void findByNameCustomMethod() {
		List<Course> courses = cr.findByName("English");
		logger.info("All courses sorted by name and id-> {}", courses);
	}

}
