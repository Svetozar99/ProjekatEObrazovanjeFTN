package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.TeacherRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeacherServiceI;

@Service
public class TeacherService implements TeacherServiceI {

	@Autowired
	TeacherRepository teacherRepos;
	
	@Override
	public List<Teacher> findAll() {
		return teacherRepos.findAll();
	}

	@Override
	public Teacher findById(Long id) {
		// TODO Auto-generated method stub
		return teacherRepos.getOne(id);
	}

	@Override
	public Teacher save(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherRepos.save(teacher);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		teacherRepos.deleteById(id);
	}

}