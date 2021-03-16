package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;

public interface TeacherServiceI {

	public List<Teacher> findAll();
	
	public Teacher findById(Long id);
	
	public Teacher save(Teacher teacher);
	
	public void delete(Long id);
}
