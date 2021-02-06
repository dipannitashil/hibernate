package com.dipannita.hibernatedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dipannita.hibernatedemo.entity.Course;

@SpringBootTest
public class CriteriaQueriesTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	@Test
	@Transactional 
	void criteriaAllCourses() {
		// select all courses
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> query = cb.createQuery(Course.class);
		// root of query (Table)
		Root<Course> courseRoot = query.from(Course.class);
		
		TypedQuery<Course> createQuery = em.createQuery(query.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Result list -> {}", resultList);
		
	}
	
	@Test
	@Transactional 
	void criteriaCoursesNameLike() {
		// select all courses where name like '%h%'
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> query = cb.createQuery(Course.class);
		// root of query (Table)
		Root<Course> courseRoot = query.from(Course.class);
		
		// define predicate
		Predicate like = cb.like(courseRoot.get("name"), "%h%");
		
		// add predicate to criteria query
		query.where(like);
		
		TypedQuery<Course> createQuery = em.createQuery(query.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Result list -> {}", resultList);
		
	}
	
	@Test
	@Transactional 
	void criteriaCoursesWithoutStudents() {
		// select all courses where course students empty
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> query = cb.createQuery(Course.class);
		// root of query (Table)
		Root<Course> courseRoot = query.from(Course.class);
		
		// define predicate
		Predicate like = cb.isEmpty(courseRoot.get("students"));
		
		// add predicate to criteria query
		query.where(like);
		
		TypedQuery<Course> createQuery = em.createQuery(query.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Result list -> {}", resultList);
		
	}

	@Test
	@Transactional 
	void join() {
		// select c from Course c join c.students s
		// 1 .use criteria builder to create a Criteria query
		// returning expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> query = cb.createQuery(Course.class);
		
		// 2. Define roots for tables involved in the query
		Root<Course> courseRoot = query.from(Course.class);
		
		// 3. Define predicates etc using criteria builder
		Join<Object, Object> join = courseRoot.join("students");
		
		// 4. Add predicates etc. to criteria query
		
		// 5. Build TypedQuery using em and criteria query
		TypedQuery<Course> createQuery = em.createQuery(query.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Result list -> {}", resultList);
		
	}
	
	@Test
	@Transactional 
	void leftJoin() {
		// select c from Course c join c.students s
		// 1 .use criteria builder to create a Criteria query
		// returning expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> query = cb.createQuery(Course.class);
		
		// 2. Define roots for tables involved in the query
		Root<Course> courseRoot = query.from(Course.class);
		
		// 3. Define predicates etc using criteria builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		// 4. Add predicates etc. to criteria query
		
		// 5. Build TypedQuery using em and criteria query
		TypedQuery<Course> createQuery = em.createQuery(query.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Result list -> {}", resultList);
		
	}

}
