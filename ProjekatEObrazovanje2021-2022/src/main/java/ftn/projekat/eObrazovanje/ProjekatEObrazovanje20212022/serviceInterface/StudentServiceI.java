package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;

public interface StudentServiceI {
	
	public Page<Student> findAll(Pageable page);
	
	public Student findById(Long id);
	
	public Student save(Student student);
	
	public void delete(Long id);
	
	public Student findByUser(String username);
	
	public Long maxId();
	
	List<Student> findByCourseInstance(Long idCourseInstance);
	
	List<Student> findOtherStudents(Long idCourseInstance);
	
	public Long count();
}
