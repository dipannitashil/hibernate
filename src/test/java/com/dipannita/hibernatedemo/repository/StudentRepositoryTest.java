package com.dipannita.hibernatedemo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.dipannita.hibernatedemo.entity.Course;
import com.dipannita.hibernatedemo.entity.Passport;
import com.dipannita.hibernatedemo.entity.Student;

@SpringBootTest
public class StudentRepositoryTest {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository sr; 
	
	@Autowired
	EntityManager em;
	
	@Test
	/**
	 * Without this the transaction ends at em.find
	 * and we get LazyInitializationException 
	 * if we are doing LAZY fetching
	 */
	@Transactional
	void findById_test() {
		Student student = em.find(Student.class, 20001l);
		/**
		 * on debugging, we see that student also contains passport object
		 * this is EAGER FETCHING (Default)
		 * a join is performed even if we dont use passport
		 * of student
		 */ 
		logger.info("student -> {}", student.getName());
		// join performed at this point
		logger.info("student passport -> {}", student.getPassport());
	}
	
	@Test
	@Transactional
	@DirtiesContext
	void complex_test() {
		Student student = em.find(Student.class, 20001l);
		logger.info("student -> {}", student.getName());
		// join performed at this point
		Passport passport = student.getPassport();
		logger.info("student passport -> {}", passport);
		
		passport.setName("EXCD23456");
		student.setName("Aimee Gibbs");
	}
	
	@Test
	@Transactional
	void retrieveStudentCourses_test() {
		Student student = em.find(Student.class, 20001l);
		logger.info("student -> {}", student.getName());
		
		logger.info("student courses -> {}", student.getCourses());
	}
	
	@Test
	@Transactional
	@DirtiesContext
	void addStudentCourse_test() {
		Student student = new Student("Shahrukh");
		Course course = new Course("Biology");
		sr.saveStudentWithCourse(student, course);
		logger.info("student -> {}", student.getName());
		
		logger.info("student courses -> {}", student.getCourses());
	}

}
