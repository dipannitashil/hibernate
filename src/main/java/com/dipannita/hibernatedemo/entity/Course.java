package com.dipannita.hibernatedemo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

/**
 * Table and Entity names are same. If we want table name to be different
 * use @Table("name")
 */
@Entity

// @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c")
// for multiple name queries
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c"),
		@NamedQuery(name = "query_get_all_courses_like_h", query = "Select c from Course c where name like '%h'") })
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * will throw exception if this column's value is null other possible attributes
	 * include unique, insertable, updatable, length(for strings), precision and
	 * scale (for numbers)
	 */
	@Column(name = "fullname", nullable = false)
	private String name;

	@CreatedDate
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedTime;
	
	/**
	 * here fetch type is by default
	 * lazy (on one to many side)
	 * on many to one side
	 * it is always eager fetching
	 *  *ToMany is lazy
	 *  *ToOne is eager
	 */
	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();
	
	/**
	 * Student will be the owning side 
	 * of the relationship
	 * so join table will be student_courses
	 * name can me changed by adding @JoinTable
	 * on owning side.
	 */
	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", createdDate=" + createdDate + ", lastUpdatedTime="
				+ lastUpdatedTime + "]";
	}

	public Course() {
	}

	public Course(String name) {
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	
}
