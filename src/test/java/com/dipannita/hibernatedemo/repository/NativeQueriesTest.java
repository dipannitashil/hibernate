package com.dipannita.hibernatedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dipannita.hibernatedemo.entity.Course;

@SpringBootTest
public class NativeQueriesTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void testNativeQueries_basic() {
		// native queries dont use @Where annotation
		// need to add clause yourself
//		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		Query query = em.createNativeQuery("SELECT * FROM COURSE where is_deleted= false", Course.class);
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	void testNativeQueries_params() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
		query.setParameter(1, 10001);
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	void testNativeQueries_named_params() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
		query.setParameter("id", 10001);
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	@Transactional
	void testNativeQueries_update() {
		Query query = em.createNativeQuery("UPDATE COURSE SET last_updated_time = sysdate() where id = :id ");
		query.setParameter("id", 10001);
		int rowsUpdated = query.executeUpdate();
		logger.info("Rows updated -> {}", rowsUpdated);
	}

}
