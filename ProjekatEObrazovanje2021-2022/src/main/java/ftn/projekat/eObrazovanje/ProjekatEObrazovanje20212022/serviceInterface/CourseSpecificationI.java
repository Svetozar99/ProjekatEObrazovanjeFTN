package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;

public interface CourseSpecificationI {

	public List<CourseSpecification> findAll();
	
	public CourseSpecification findById(Long id);
	
	public CourseSpecification save(CourseSpecification courseSpecification);
	
	public void delete(Long id);

}
