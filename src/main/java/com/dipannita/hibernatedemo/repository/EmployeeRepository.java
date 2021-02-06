package com.dipannita.hibernatedemo.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dipannita.hibernatedemo.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	EntityManager em;

	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	public List<Employee> getAllEmployees() {
		// done using union in Table per class strategy!
		// wont work in mapped superclass as Employee is no longer an entity!
//		return em.createQuery("select e FROM Employee e", Employee.class).getResultList();
		return Collections.emptyList();
		
	}
	
	public List<Employee> getAllFullTimeEmployees() {
		return em.createQuery("select e FROM FullTmeEmployee e", Employee.class).getResultList();		
	}
	
	public List<Employee> getAllPartTimeEmployees() {
		return em.createQuery("select e FROM PartTimeEmployee e", Employee.class).getResultList();		
	}
	
	public Employee findById(long id) {
		return em.find(Employee.class, id);
	}

}
