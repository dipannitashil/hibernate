package com.dipannita.hibernatedemo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.dipannita.hibernatedemo.entity.Course;
import com.dipannita.hibernatedemo.entity.Review;
import com.dipannita.hibernatedemo.entity.Review.ReviewRating;
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
		cr.deleteById(10004);
		
		assertNull(cr.findById(10004));
	}
	
	@Test
	@DirtiesContext // reset data for other tests
	void save_test() {
		Course course = cr.save(new Course("Hindi"));
		
		assertNotNull(course.getId());
	}
	
	@Test
	@DirtiesContext // reset data for other tests
	void update_test() {
		Course course = cr.findById(10001);
		course.setName("English Advannced");
		Course courseUpdated = cr.save(course);
		
		assertEquals("English Advannced", courseUpdated.getName());
	}
	
	@Test
	@Transactional
	@DirtiesContext 
	void saveReview_test() {
		Course courseInitial = cr.findById(10003l);
		logger.info("Reviews before inserting ->"+ courseInitial.getReviews());
		int initialSize = courseInitial.getReviews().size();
		
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review(null, ReviewRating.THREE));
		reviews.add(new Review(null, ReviewRating.THREE));
		cr.addReviewsForCourse(10003L, reviews);
		
		Course course = cr.findById(10003l);
		logger.info("Reviews after inserting ->"+ course.getReviews());
		
		assertEquals(initialSize+2, course.getReviews().size());
		
	}

}
