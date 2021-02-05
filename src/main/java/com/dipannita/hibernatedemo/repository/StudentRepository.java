package com.dipannita.hibernatedemo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dipannita.hibernatedemo.entity.Course;
import com.dipannita.hibernatedemo.entity.Passport;
import com.dipannita.hibernatedemo.entity.Student;

@Repository
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	EntityManager em;
	
	public Student findById(long id) {
		return em.find(Student.class, id);
	}
	
	/**
	 * since we are doing two steps in one method
	 * it will throw exception if
	 * not annotated with transactional
	 */
	@Transactional
	public void deleteById(long id) {
		Student student = em.find(Student.class, id);
		em.remove(student);
	}
	
	@Transactional
	public Student save(Student student) {
		if(student.getId()==null)
			em.persist(student);
		else
			em.merge(student);
		return student;
	}
	
	@Transactional
	public void playWithEntityManager() {
		Student student1 = new Student("Maeve");
		em.persist(student1);
		em.flush();
		
		Student student2 = new Student("Otis");
		em.persist(student2);
		em.flush();
		

		Student student3 = new Student("Eric");
		em.persist(student3);
		em.flush();	
	}
	
	@Transactional
	public void saveStudentWithPassport() {
		Passport passport1 = new Passport("EDFG2345");
		/**
		 * if we dont save passport saved first
		 * we get Exception because
		 * passport is still transient.
		 * The message is
		 * save the transient instance before flushing
		 */
		em.persist(passport1);
		Student student1 = new Student("Aimee");
		student1.setPassport(passport1);
		em.persist(student1);
		em.flush();
	}
	
	@Transactional
	public void saveStudentWithCourse(Student student, Course course) {
		
		em.persist(student);
		em.persist(course);
		student.addCourse(course);
		course.addStudent(student);
		
	}

}
