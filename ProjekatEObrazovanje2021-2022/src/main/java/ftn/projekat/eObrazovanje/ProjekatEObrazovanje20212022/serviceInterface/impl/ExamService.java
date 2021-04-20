package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.ExamRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamServiceInterface;

@Service
public class ExamService implements ExamServiceInterface {

	@Autowired
	ExamRepository examRepository;
	
	@Override
	public List<Exam> findAll() {
		// TODO Auto-generated method stub
		return examRepository.findAll();
	}

	@Override
	public Exam findById(Long id) {
		// TODO Auto-generated method stub
		return examRepository.getOne(id);
	}

	@Override
	public Exam save(Exam exam) {
		// TODO Auto-generated method stub
		return examRepository.save(exam);
	}

	@Override
	public void delete(Long id) {
		examRepository.deleteById(id);
	}

	@Override
	public List<Exam> examPassedForStudent(String cardNum) {
		// TODO Auto-generated method stub
		return examRepository.findOneByEnrollment_student_cardNumber(cardNum);
	}

	@Override
	public Exam examOneByUsernameAndId(String username, Long id) {
		// TODO Auto-generated method stub
		return examRepository.findOneByEnrollment_student_user_usernameAndId(username, id);
	}

}
