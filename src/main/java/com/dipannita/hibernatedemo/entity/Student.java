package com.dipannita.hibernatedemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToOne(fetch = FetchType.LAZY) // passport_id foreign key
	private Passport passport;
	
	@ManyToMany
	@JoinTable(name = "student_course", 
	joinColumns = @JoinColumn(name = "student_id"), 
	inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", passport=" + passport + ", courses=" + courses + "]";
	}

	public Student() {
		super();
	}

	public Student(String name) {
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

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	
}
