package com.dipannita.hibernatedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dipannita.hibernatedemo.entity.Course;

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
	void testJpql_basic() {
		Query query = em.createQuery("Select c from Course c ");
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	void testJpql_typed() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c ", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	void testJpql_where() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where name like '%h'", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	void testJpql_namedQuery() {
		TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	

}
