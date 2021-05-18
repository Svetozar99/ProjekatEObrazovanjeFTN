package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;

public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
	
	@Query(value = "SELECT * FROM eobrazovanje.course_instance\r\n" + 
			"	where id in (SELECT course_instance_id FROM eobrazovanje.teachings\r\n" + 
			"					where teacher_id in (SELECT id FROM eobrazovanje.teachers\r\n" + 
			"											where user_id in (SELECT id FROM eobrazovanje.users\r\n" + 
			"												where username LIKE :userName)));", nativeQuery = true)
	List<CourseInstance> findByTeacher(@Param("userName") String username);

}
