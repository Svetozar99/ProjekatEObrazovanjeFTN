package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;

public interface ExamServiceInterface {

	public List<Exam> findAll();
	
	public Exam findById(Long id);
	
	public Exam save(Exam exam);
	
	public void delete(Long id);
	
	public List<Exam> examPassedForStudent(String cardNum);
}
