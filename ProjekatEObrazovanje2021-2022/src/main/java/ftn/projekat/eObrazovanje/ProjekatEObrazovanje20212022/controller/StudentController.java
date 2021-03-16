package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.AccountDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.StudentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceInterface;

@RestController
@RequestMapping(value = "api/students")
public class StudentController {

	@Autowired
	public StudentServiceInterface studentServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents(){
		List<Student> students = studentServiceInterface.findAll();
		
		List<StudentDTO> dtos = new ArrayList<StudentDTO>();
		
		for (Student s : students) {
			dtos.add(new StudentDTO(s));
		}
		return new ResponseEntity<List<StudentDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> getOneStudent(@PathParam("id") Long id){
		Student student = studentServiceInterface.findById(id);
		
		return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
		User user = new User();
		Student student = new Student();
		student.setCardNumber(studentDTO.getCardNumber());
		student.setUser(user);
		
		student = studentServiceInterface.save(student);
		
		return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteStudent(@PathParam("id") Long id){
		Student student = studentServiceInterface.findById(id);
		if(student != null) {
			studentServiceInterface.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
