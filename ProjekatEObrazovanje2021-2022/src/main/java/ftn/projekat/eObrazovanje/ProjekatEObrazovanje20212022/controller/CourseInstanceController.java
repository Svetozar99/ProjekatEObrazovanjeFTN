package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.CourseInstanceDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseInstanceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseSpecificationI;

@RestController
@RequestMapping(value = "api/course-instance")
public class CourseInstanceController {

	@Autowired
	private CourseInstanceI ci;
	
	@Autowired
	private CourseSpecificationI cs;
	
	@GetMapping
	public ResponseEntity<List<CourseInstanceDTO>> getAll(){
		List<CourseInstance> cis = ci.getAll();
		
		List<CourseInstanceDTO> cisdto = new ArrayList<CourseInstanceDTO>();
		
		for(CourseInstance ci : cis) {
			cisdto.add(new CourseInstanceDTO(ci));
		}
		return new ResponseEntity<List<CourseInstanceDTO>>(cisdto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CourseInstanceDTO> getOne(@PathVariable("id") Long id){
		CourseInstance cis = ci.findById(id);
		if(cis == null) {
			return new ResponseEntity<CourseInstanceDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CourseInstanceDTO>(new CourseInstanceDTO(cis), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<CourseInstanceDTO> update(@RequestBody CourseInstanceDTO cid){
		CourseSpecification c = cs.findById(cid.getCourseSpecificationDTO().getId());
		
		CourseInstance cit = ci.findById(cid.getId());
		
		if(cit == null) {
			return new ResponseEntity<CourseInstanceDTO>(HttpStatus.NOT_FOUND);
		}
		cit.setStartDate(cid.getStartDate());
		cit.setEndDate(cid.getEndDate());
		cit.setCourseSpecification(c);
		cit = ci.save(cit);
		return new ResponseEntity<CourseInstanceDTO>(new CourseInstanceDTO(cit), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<CourseInstanceDTO> save(@RequestBody CourseInstanceDTO cid){
		CourseSpecification c = cs.findById(cid.getCourseSpecificationDTO().getId());
		
		CourseInstance cit = new CourseInstance();
		cit.setStartDate(cid.getStartDate());
		cit.setEndDate(cid.getEndDate());
		cit.setCourseSpecification(c);
		cit = ci.save(cit);
		return new ResponseEntity<CourseInstanceDTO>(new CourseInstanceDTO(cit), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		CourseInstance cit = ci.findById(id);
		if(cit != null) {
			ci.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}