package com.dipannita.hibernatedemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dipannita.hibernatedemo.entity.Course;

@RepositoryRestResource(path="courses") // NOT FOR PRODUCTION!!
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

	List<Course> findByName(String string);
	List<Course> findByNameOrderById(String string);
	List<Course> deleteByName(String string);
	
	@Query("select c from Course c where name like '%en%'") // JPQL
	List<Course> findCoursesNameLike(String string);
	
	@Query(value = "select * from course c where name like '%en%'", nativeQuery = true) 
	List<Course> findCoursesNameLikeNativeQuery(String string);
	
	// named query
	@Query(name = "query_get_all_courses") 
	List<Course> findCoursesNameLikeNamedQuery(String string);

}
