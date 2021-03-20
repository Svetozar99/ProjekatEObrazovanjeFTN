package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.CourseInstanceRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseInstanceI;

@Service
public class CourseInstanceService implements CourseInstanceI {

	@Autowired
	CourseInstanceRepository cir;
	
	@Override
	public List<CourseInstance> getAll() {
		// TODO Auto-generated method stub
		return cir.findAll();
	}

	@Override
	public CourseInstance findById(Long id) {
		// TODO Auto-generated method stub
		return cir.getOne(id);
	}

	@Override
	public CourseInstance save(CourseInstance ci) {
		// TODO Auto-generated method stub
		return cir.save(ci);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cir.deleteById(id);
	}

}
