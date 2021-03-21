package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
