package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.EnrollmentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseInstanceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.EnrollmentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;

@RestController
@RequestMapping(value = "api/enrollment")
public class EnrollmentController {

	@Autowired
	EnrollmentServiceI e;
	
	@Autowired
	StudentServiceI s;
	
	@Autowired
	CourseInstanceI c;
	
	@GetMapping
	public ResponseEntity<List<EnrollmentDTO>> getAll(){
		List<Enrollment> enrollments = e.findAll();
		
		List<EnrollmentDTO> endto = new ArrayList<EnrollmentDTO>();
		
		for(Enrollment e: enrollments) {
			endto.add(new EnrollmentDTO(e));
		}
		return new ResponseEntity<List<EnrollmentDTO>>(endto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EnrollmentDTO> getOne(@PathVariable("id") Long id){
		Enrollment enrollment = e.getOne(id);
		
		return new ResponseEntity<EnrollmentDTO>(new EnrollmentDTO(enrollment), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EnrollmentDTO> update(@RequestBody EnrollmentDTO edto, @PathVariable("id") Long id){
		Student student = s.findById(edto.getStudentDTO().getId());
		CourseInstance cii = c.findById(edto.getCourseInstanceDTO().getId());
		
		Enrollment enr = e.getOne(id);
		if(enr == null) {
			return new ResponseEntity<EnrollmentDTO>(HttpStatus.BAD_REQUEST);
		}
		enr.setStudent(student);
		enr.setCourseInstance(cii);
		enr = e.save(enr);
		return new ResponseEntity<EnrollmentDTO>(new EnrollmentDTO(enr), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<EnrollmentDTO> add(@RequestBody EnrollmentDTO edto){
		Student student = s.findById(edto.getStudentDTO().getId());
		CourseInstance cii = c.findById(edto.getCourseInstanceDTO().getId());
		
		Enrollment enr = new Enrollment();
		enr.setStudent(student);
		enr.setCourseInstance(cii);
		enr = e.save(enr);
		return new ResponseEntity<EnrollmentDTO>(new EnrollmentDTO(enr), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		Enrollment enr = e.getOne(id);
		if(enr != null) {
			e.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
