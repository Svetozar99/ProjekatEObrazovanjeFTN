package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.ExamPartRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartServiceInterface;

@Service
public class ExamPartService implements ExamPartServiceInterface {

	@Autowired
	ExamPartRepository examPartRepository;
	
	@Override
	public List<ExamPart> findAll() {
		// TODO Auto-generated method stub
		return examPartRepository.findAll();
	}
	
	@Override
	public List<ExamPart> findByCode(String code) {
		// TODO Auto-generated method stub
		return examPartRepository.findByCode(code);
	}

	@Override
	public ExamPart findById(Long id) {
		// TODO Auto-generated method stub
		return examPartRepository.getOne(id);
	}

	@Override
	public ExamPart save(ExamPart examPart) {
		// TODO Auto-generated method stub
		return examPartRepository.save(examPart);
	}

	@Override
	public void delete(Long id) {
		examPartRepository.deleteById(id);
	}

	@Override
	public List<ExamPart> examPartPassedForStudent(String cardNumber) {
		// TODO Auto-generated method stub
		return examPartRepository.findOneByExam_enrollment_student_cardNumber(cardNumber);
	}

	@Override
	public List<ExamPart> findByCodeAndCardNum(String code, String cardNum) {
		// TODO Auto-generated method stub
		return examPartRepository.findByExamPartStatus_codeAndExam_enrollment_student_cardNumber(code, cardNum);
	}

	@Override
	public List<ExamPart> findByCardNumAndCourse(String cardNum, Long id) {
		// TODO Auto-generated method stub
		return examPartRepository.findByExam_enrollment_student_cardNumberAndExam_enrollment_courseInstance_id(cardNum, id);
	}

	@Override
	public List<ExamPart> findByCourseInstance(Long courseId) {
		return examPartRepository.findByExam_enrollment_courseInstance_id(courseId);
	}

	@Override
	public long maxId() {
		return examPartRepository.maxId();
	}
	
	@Override
	public boolean isIn(ExamPart examPart,List<ExamPartDTO> dtos) {
		for (ExamPartDTO examPartDTO : dtos) {
			if(examPart.getCode().equals(examPartDTO.getCode())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ExamPart> findByTeacher(String username) {
		// TODO Auto-generated method stub
		return examPartRepository.findByTeacher(username);
	}

}
