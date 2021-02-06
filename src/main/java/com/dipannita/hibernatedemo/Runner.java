package com.dipannita.hibernatedemo;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipannita.hibernatedemo.entity.FullTmeEmployee;
import com.dipannita.hibernatedemo.entity.PartTimeEmployee;
import com.dipannita.hibernatedemo.repository.CourseRepository;
import com.dipannita.hibernatedemo.repository.EmployeeRepository;
import com.dipannita.hibernatedemo.repository.StudentRepository;

@Service
public class Runner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EmployeeRepository er;
	
	public void run() {
//		Course course = courseRepository.findById(10001);
//		logger.info("Course 10001- {}", course);
//		studentRepository.saveStudentWithPassport();
		
//		er.insert(new FullTmeEmployee("Dips", new BigDecimal(1000l)));
//		er.insert(new PartTimeEmployee("Dipa", new BigDecimal(1000l)));
//		logger.info("Employees -> {}", er.getAllEmployees());
	}

}
