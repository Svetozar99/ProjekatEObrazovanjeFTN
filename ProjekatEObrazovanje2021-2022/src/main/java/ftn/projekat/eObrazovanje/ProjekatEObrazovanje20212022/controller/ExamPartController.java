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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartStatusServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartTypeServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamServiceInterface;

@RestController
@RequestMapping(value = "api/exam-part")
public class ExamPartController {

	@Autowired
	private ExamPartServiceInterface examPartS;
	
	@Autowired
	private ExamServiceInterface examS;
	
	@Autowired
	private ExamPartTypeServiceInterface examPartTypeS;
	
	@Autowired
	private ExamPartStatusServiceInterface examPartStatusS;
	
	@GetMapping
	public ResponseEntity<List<ExamPartDTO>> getAllExamParts(){
		List<ExamPart> examParts = examPartS.findAll();
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		for (ExamPart examPart : examParts) {
			dtos.add(new ExamPartDTO(examPart));
		}
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ExamPartDTO> getOneExamPart(@PathVariable("id") Long id){
		ExamPart examPart = examPartS.findById(id);
		if(examPart == null) {
			return new ResponseEntity<ExamPartDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExamPartDTO>(new ExamPartDTO
				(examPart), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<ExamPartDTO> updateExamPart(@RequestBody ExamPartDTO dto){
		ExamPart examPart = examPartS.findById(dto.getId());
		Exam exam = examS.findById(dto.getExamDTO().getId());
		ExamPartType examPartType = examPartTypeS.findById(dto.getExamPartTypeDTO().getId());
		ExamPartStatus examPartStatus = examPartStatusS.findById(dto.getStatusDTO().getId());
		if(examPart == null) {
			return ResponseEntity.notFound().build();
		}
		examPart.setDate(dto.getDate());
		examPart.setLocation(dto.getLocation());
		examPart.setPoints(dto.getPoints());
		examPart.setExam(exam);
		examPart.setExamPartType(examPartType);
		examPart.setExamPartStatus(examPartStatus);
		
		examPart = examPartS.save(examPart);
		
		return new ResponseEntity<ExamPartDTO>(new ExamPartDTO(examPart), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ExamPartDTO> saveExamPart(@RequestBody ExamPartDTO dto){
		ExamPart examPart = new ExamPart();
		Exam exam = examS.findById(dto.getExamDTO().getId());
		ExamPartType examPartType = examPartTypeS.findById(dto.getExamPartTypeDTO().getId());
		ExamPartStatus examPartStatus = examPartStatusS.findById(dto.getStatusDTO().getId());
		
		examPart.setDate(dto.getDate());
		examPart.setLocation(dto.getLocation());
		examPart.setPoints(dto.getPoints());
		examPart.setExam(exam);
		examPart.setExamPartType(examPartType);
		examPart.setExamPartStatus(examPartStatus);
		
		examPart = examPartS.save(examPart);
			
		return new ResponseEntity<ExamPartDTO>(new ExamPartDTO(examPart), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteExamPart(@PathVariable("id") Long id){
		ExamPart examPart = examPartS.findById(id);
		if(examPart != null) {
			examPartS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
