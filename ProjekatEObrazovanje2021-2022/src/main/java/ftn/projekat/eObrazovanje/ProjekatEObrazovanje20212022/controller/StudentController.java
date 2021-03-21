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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.StudentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserServiceI;

@RestController
@RequestMapping(value = "api/student")
public class StudentController {

	@Autowired
	private StudentServiceI studentService;
	
	@Autowired
	private UserServiceI userService;
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents(){
		List<Student> students = studentService.findAll();
		
		List<StudentDTO> dtos = new ArrayList<StudentDTO>();
		
		for (Student s : students) {
			dtos.add(new StudentDTO(s));
		}
		return new ResponseEntity<List<StudentDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<StudentDTO> getOneStudent(@PathVariable("id") Long id){
		System.out.println("usao u f-ju");
		
		System.out.println(id + " student get id");
		Student student = studentService.findById(id);
		
		
		
		if(student == null) {
			System.out.println("student je null");
			return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
		User user = userService.findById(studentDTO.getUserDTO().getId());
		Student student = studentService.findById(studentDTO.getId());
		if(student == null) {
			return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
		}
		student.setCardNumber(studentDTO.getCardNumber());
		student.setUser(user);
		studentService.save(student);
		return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
		User user = userService.findById(studentDTO.getUserDTO().getId());
		Student student = new Student();
		student.setCardNumber(studentDTO.getCardNumber());
		student.setUser(user);
		student = studentService.save(student);
		
		return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id){
		Student student = studentService.findById(id);
		if(student != null) {
			studentService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
