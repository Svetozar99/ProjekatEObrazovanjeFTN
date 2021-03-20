package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.CourseSpecificationRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseSpecificationI;

@Service
public class CourseSpecificationService implements CourseSpecificationI {

	@Autowired
	CourseSpecificationRepository csrepos;
	
	@Override
	public List<CourseSpecification> findAll() {
		// TODO Auto-generated method stub
		return csrepos.findAll();
	}

	@Override
	public CourseSpecification findById(Long id) {
		// TODO Auto-generated method stub
		return csrepos.getOne(id);
	}

	@Override
	public CourseSpecification save(CourseSpecification courseSpecification) {
		// TODO Auto-generated method stub
		return csrepos.save(courseSpecification);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		csrepos.deleteById(id);
	}

}
