package com.dipannita.hibernatedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipannita.hibernatedemo.entity.Course;
import com.dipannita.hibernatedemo.repository.CourseRepository;
import com.dipannita.hibernatedemo.repository.StudentRepository;

@Service
public class Runner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	public void run() {
//		Course course = courseRepository.findById(10001);
//		logger.info("Course 10001- {}", course);
//		studentRepository.saveStudentWithPassport();
	}

}
