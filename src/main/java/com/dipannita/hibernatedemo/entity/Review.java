package com.dipannita.hibernatedemo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	public enum ReviewRating {
		ONE, TWO, THREE, FOUR, FIVE;
	}

	@Id
	@GeneratedValue
	private Long id;

	private String description;
	// so that we dont depend on ordinals
	// ordinals depend on position of the enum!
	@Enumerated(EnumType.STRING)
	private ReviewRating rating;
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", description=" + description + ", rating=" + rating + ", course=" + course + "]";
	}

	// review will have course id
	// so this is the owning side of the relationship
	@ManyToOne
	private Course course;

	public Review() {
		super();
	}

	public Review(String description, ReviewRating rating) {
		super();
		this.description = description;
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
