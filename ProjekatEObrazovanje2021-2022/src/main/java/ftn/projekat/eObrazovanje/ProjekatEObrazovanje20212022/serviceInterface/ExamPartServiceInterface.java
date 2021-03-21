package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;


public interface ExamPartServiceInterface {

	public List<ExamPart> findAll();
	
	public ExamPart findById(Long id);
	
	public ExamPart save(ExamPart examPart);
	
	public void delete(Long id);
}
