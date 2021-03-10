package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;

public interface ExamPartRepository extends JpaRepository<ExamPart, Long> {

	List<ExamPart> findByExam_id(Long id);
}
