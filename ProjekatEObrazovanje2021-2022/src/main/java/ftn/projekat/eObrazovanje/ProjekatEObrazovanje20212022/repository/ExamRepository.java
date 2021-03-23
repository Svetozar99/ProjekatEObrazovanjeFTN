package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

	List<Exam> findByEnrollment_courseInstance_courseSpecification_code(String code);
	
	List<Exam> findOneByEnrollment_student_cardNumber(String cardNumber);
}
