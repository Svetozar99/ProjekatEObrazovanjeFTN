package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findOneByCardNumber(String cardNumber);
	Student findOneByUser_username(String username);
	
	@Query(value = "SELECT * FROM eobrazovanje.students\r\n" + 
			"where id in (SELECT student_id FROM eobrazovanje.enrollments\r\n" + 
			"where course_instance_id=:idCourseInstance)", nativeQuery = true)
	Page<Student> findByCourseInstance(@Param("idCourseInstance") Long idCourseInstance,Pageable page);
	
	@Query(value = "SELECT count(*) FROM eobrazovanje.students\r\n" + 
			"where id in (SELECT student_id FROM eobrazovanje.enrollments\r\n" + 
			"where course_instance_id=:idCourseInstance)", nativeQuery = true)
	Long countByCourseInstance(@Param("idCourseInstance") Long idCourseInstance);
	
	@Query(value = "SELECT * FROM eobrazovanje.students\r\n" + 
			"where id not in (SELECT student_id FROM eobrazovanje.enrollments\r\n" + 
			"where course_instance_id=:idCourseInstance)", nativeQuery = true)
	List<Student> findOtherStudents(@Param("idCourseInstance") Long idCourseInstance);
	
	@Query("SELECT max(id) FROM Student")
	Long maxID();
}
