package com.dipannita.hibernatedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dipannita.hibernatedemo.entity.Course;
import com.dipannita.hibernatedemo.entity.Review;

@Repository
public class CourseRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	EntityManager em;

	public Course findById(long id) {
		return em.find(Course.class, id);
	}

	/**
	 * since we are doing two steps in one method it will throw exception if not
	 * annotated with transactional
	 */
	@Transactional
	public void deleteById(long id) {
		Course course = em.find(Course.class, id);
		em.remove(course);
	}

	/**
	 * If not annotated we get InvalidDataAccessUsageApiException with msg No
	 * EntityManager with actual transaction available for current thread - cannot
	 * reliably process 'persist' call
	 */
	@Transactional
	public Course save(Course course) {
		if (course.getId() == null)
			em.persist(course);
		else
			em.merge(course);
		return course;
	}

	@Transactional
	public void playWithEntityManager() {
		Course course1 = new Course("Physics");
		em.persist(course1);
		// this change is tracked by EntityManager
		course1.setName("Physics L2");

		em.flush();

		Course course2 = new Course("Chemistry");
		em.persist(course2);
		em.flush();

		em.detach(course2);
		logger.info("detaching course2!");
		// this change is NOT tracked by EntityManager
		course2.setName("Chemistry L2");
		logger.info("re-attaching course2!");
		em.merge(course2);
		em.flush();

		// detach all entities
//		em.clear();

		// refresh behaviour

		Course course3 = new Course("Biology");
		em.persist(course3);
		em.flush();

		course3.setName("Biology L2"); // change will be effectively lost
		// refreshing here will get data from db,
		// resetting any changes we made
		em.refresh(course3);
		em.flush();
	}

	@Transactional
	public void addReviewsForCourse(Long id, List<Review> reviews) {
		Course course = findById(id);
		logger.info("Reviews -> {}", course.getReviews());
		reviews.stream().forEach(r -> {
			course.addReview(r);
			r.setCourse(course);
			em.persist(r);
		});

	}
}
