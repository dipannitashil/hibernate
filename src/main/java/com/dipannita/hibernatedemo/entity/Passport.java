package com.dipannita.hibernatedemo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	/**
	 * mappedBy makes Student
	 * the owning side of the relationship
	 * without mappedBy, passport will also contain a foreign key
	 * named student_id
	 * with mappedBy, you can also navigate to student
	 *  without the need for duplicate data
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy= "passport") // bidirectional relationship
	private Student student;

	public Passport() {
		super();
	}

	public Passport(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
