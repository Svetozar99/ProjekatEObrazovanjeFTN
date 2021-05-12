package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;

public interface ExamPartServiceInterface {

	public List<ExamPart> findAll();
	
	public ExamPart findById(Long id);
	
	public ExamPart save(ExamPart examPart);
	
	public void delete(Long id);
	
	public List<ExamPart> examPartPassedForStudent(String cardNumber);
	
	public List<ExamPart> findByCodeAndCardNum(String code, String cardNum);
	
	public List<ExamPart> findByCardNumAndCourse(String cardNum,Long id);
	
	public List<ExamPart> findByCourseInstance(Long courseId);
	
	public long maxId();
	
	public boolean isIn(ExamPart examPart,List<ExamPartDTO> dtos);
}
