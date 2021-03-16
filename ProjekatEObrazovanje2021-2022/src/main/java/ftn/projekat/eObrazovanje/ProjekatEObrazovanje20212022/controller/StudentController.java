package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserServiceInterface;

@RestController
@RequestMapping(value = "api/student")
public class StudentController {

	@Autowired
	private StudentServiceInterface studentServiceInterface;
	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents(){
		List<Student> students = studentServiceInterface.findAll();
		
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
		Student student = studentServiceInterface.findById(id);
		
		
		
		if(student == null) {
			System.out.println("student je null");
			return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
		User user = userServiceInterface.findById(studentDTO.getUserDTO().getId());
		Student student = new Student();
		student.setCardNumber(studentDTO.getCardNumber());
		student.setUser(user);
		student = studentServiceInterface.save(student);
		
		return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id){
		Student student = studentServiceInterface.findById(id);
		if(student != null) {
			studentServiceInterface.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
