package com.dipannita.hibernatedemo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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

}
