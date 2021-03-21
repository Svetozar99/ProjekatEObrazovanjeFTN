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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.AccountDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.EnrollmentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamServiceInterface;

@RestController
@RequestMapping(value = "api/exam")
public class ExamController {

	@Autowired
	private ExamServiceInterface examS;
	
	@Autowired
	private EnrollmentServiceI enrollmentS;
	
	@GetMapping
	public ResponseEntity<List<ExamDTO>> getAllExams(){
		List<Exam> exams = examS.findAll();
		
		List<ExamDTO> dtos = new ArrayList<ExamDTO>();
		
		for (Exam exam : exams) {
			dtos.add(new ExamDTO(exam));
		}
		return new ResponseEntity<List<ExamDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ExamDTO> getOneExam(@PathVariable("id") Long id){
		Exam exam = examS.findById(id);
		if(exam == null) {
			return new ResponseEntity<ExamDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExamDTO>(new ExamDTO(exam), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<ExamDTO> updateExam(@RequestBody ExamDTO dto){
		Exam exam = examS.findById(dto.getId());
		Enrollment enrollment = enrollmentS.findById(dto.getEnrollmentDTO().getId());
		if(exam == null) {
			return ResponseEntity.notFound().build();
		}
		exam.setPoints(dto.getPoints());
		exam.setGradle(dto.getGradle());
		exam.setEnrollment(enrollment);
		
		exam = examS.save(exam);
		
		return new ResponseEntity<ExamDTO>(new ExamDTO(exam), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ExamDTO> saveExam(@RequestBody ExamDTO dto){
		Exam exam = new Exam();
		Enrollment enrollment = enrollmentS.findById(dto.getEnrollmentDTO().getId());
		exam.setPoints(dto.getPoints());
		exam.setGradle(dto.getGradle());
		exam.setEnrollment(enrollment);
		
		exam = examS.save(exam);
		
		return new ResponseEntity<ExamDTO>(new ExamDTO(exam), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteExam(@PathVariable("id") Long id){
		Exam exam = examS.findById(id);
		if(exam != null) {
			examS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
