package com.dipannita.hibernatedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dipannita.hibernatedemo.entity.Course;
import com.dipannita.hibernatedemo.entity.Student;

@SpringBootTest
/**
 * JPQL is used to query entities
 *
 */
public class JPQLTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	@Transactional 
	// Course entity has toString which forces hibernate to fetch related students too, thats why we need transactional
	void testJpql_basic() {
		Query query = em.createQuery("Select c from Course c ");
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	@Transactional 
	void testJpql_typed() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c ", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	@Transactional 
	void testJpql_where() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where name like '%h'", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	@Transactional 
	void testJpql_namedQuery() {
		TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	@Transactional 
	void coursesWhichDontHaveStudents() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c where students empty -> {}", resultList);
	}
	
	@Test
	@Transactional 
	void coursesWhichHaveAtleast2Students() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c where students >=2 -> {}", resultList);
	}
	
	@Test
	@Transactional 
	void coursesOrderedByStudents() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c order by size(c.students) -> {}", resultList);
	}
	
	@Test
	@Transactional 
	void studentsWithPassportsHavingAPattern() {
		TypedQuery<Student> typedQuery = em.createQuery("Select s from Student s where s.passport.name like '%1234%'", Student.class);
		List<Student> resultList = typedQuery.getResultList();
		logger.info("Select s from Student s where s.passport.name like '%1234%' -> {}", resultList);
	}
	
	// join -> select c, s from Course c JOIN c.students s 
	// left join -> select c, s from Course c LEFT JOIN c.students s
	// cross join -> select c, s from Course c, Student c
	@Test
	@Transactional 
	void jpqlJoins() {
		// we get courses that have students
		Query query = em.createQuery("select c, s from Course c JOIN c.students s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("select c, s from Course c JOIN c.students s list size -> {}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("course - "+result[0]);
			logger.info("student - "+result[1]);
		}
	}

	@Test
	@Transactional 
	void jpqlLeftJoins() {
		// we get courses that may or may not have students
		Query query = em.createQuery("select c, s from Course c LEFT JOIN c.students s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("select c, s from Course c LEFT JOIN c.students s -> {}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("course - "+result[0]);
			logger.info("student - "+result[1]);
		}
	}
}
