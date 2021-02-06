package com.dipannita.hibernatedemo.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dipannita.hibernatedemo.entity.FullTmeEmployee;
import com.dipannita.hibernatedemo.entity.PartTimeEmployee;

@SpringBootTest
public class EmployeeRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeRepository er;
	
	@Test
	@Transactional
	public void insertSomeEmployees() {
		er.insert(new FullTmeEmployee("Dips", new BigDecimal(1000l)));
		er.insert(new PartTimeEmployee("Dipa", new BigDecimal(1000l)));
//		logger.info("Employees -> {}", er.getAllEmployees());
	}

}
