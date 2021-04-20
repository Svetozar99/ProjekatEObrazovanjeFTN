package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;

public interface CourseInstanceI {
	
	public List<CourseInstance> getAll();
	
	public CourseInstance findById(Long id);
	
	public CourseInstance save(CourseInstance ci);
	
	public void delete(Long id);

}
