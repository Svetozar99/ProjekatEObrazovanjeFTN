package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;

public interface ExamPartRepository extends JpaRepository<ExamPart, Long> {

	List<ExamPart> findByExam_id(Long id);
	
	List<ExamPart> findByCode(String code);
	
	List<ExamPart> findOneByExam_enrollment_student_cardNumber(String cardNumber);
	
	List<ExamPart>  findByExamPartStatus_codeAndExam_enrollment_student_cardNumber(String code, String cardNumber);
	
	List<ExamPart>  findByExam_enrollment_student_cardNumberAndExam_enrollment_courseInstance_id(String cardNumber, Long id);
	
	List<ExamPart>  findByExam_enrollment_courseInstance_id(Long courseId);
	
	@Query(value="SELECT max(id) FROM eobrazovanje.exam_parts;",nativeQuery = true)
	long maxId();
	
	@Query(value="SELECT * FROM eobrazovanje.exam_parts\r\n" + 
			"where exam_id in (SELECT id FROM eobrazovanje.exams\r\n" + 
			"	where enrollment_id in (SELECT id FROM eobrazovanje.enrollments\r\n" + 
			"		where course_instance_id in (SELECT course_instance_id FROM eobrazovanje.teachings\r\n" + 
			"										where teacher_id in (SELECT id FROM eobrazovanje.teachers\r\n" + 
			"																where user_id = (SELECT id FROM eobrazovanje.users\r\n" + 
			"																					where username LIKE :username)))))",nativeQuery = true)
	List<ExamPart>  findByTeacher(@Param("username") String username);
}
