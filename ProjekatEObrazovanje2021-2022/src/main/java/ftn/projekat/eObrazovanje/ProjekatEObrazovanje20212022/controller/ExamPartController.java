package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.security.Principal;
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
import org.springframework.web.server.ResponseStatusException;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AccountServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.EnrollmentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartStatusServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartTypeServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;

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
	

	@Autowired
	private EnrollmentServiceI enrollmentS;
	
	@Autowired
	private StudentServiceI studServ;
	
	@Autowired
	private AccountServiceI accSevice;
	
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
		examPart.setWonPoints(dto.getWonPoints());
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
		examPart.setWonPoints(0);
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
	
	@GetMapping(value = "/my-exam-parts/{code}")
	public ResponseEntity<List<ExamPartDTO>> passedExamParts(Principal principal, @PathVariable("code") String code) {
		String name = principal.getName(); //get logged in username
		Student st = studServ.findByUser(name);
//		ExamPartStatus eps = examPartStatusS.expsByCode(code);
		List<ExamPart> examparts = examPartS.findByCodeAndCardNum(code, st.getCardNumber());
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		for (ExamPart exampart : examparts) {
			dtos.add(new ExamPartDTO(exampart));
		}
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@PutMapping(value = "register-exam-part/{id}/account/{acc-id}")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
	public ResponseEntity<ExamPartDTO> registerMyExamPart(@PathVariable("id") Long id,  @PathVariable("acc-id") Long accid){
		ExamPart examPart = examPartS.findById(id);
		ExamPartStatus examPartStatus = examPartStatusS.expsByCode("re");
		if(examPart == null) {
			return ResponseEntity.notFound().build();
		}
		Account acc = accSevice.findById(accid);
		if(acc.getAmount() <= 0) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Nema dovoljno novca na računu");
		}else {
			Double amount = acc.getAmount() - 200;
			acc.setAmount(amount);
			acc = accSevice.save(acc);
		}
		examPart.setExamPartStatus(examPartStatus);		
		examPart = examPartS.save(examPart);
		
		return new ResponseEntity<ExamPartDTO>(new ExamPartDTO(examPart), HttpStatus.OK);
	}
	
	@PostMapping(value = "/register-exam-part")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> registerExamPart(@RequestBody ExamPartDTO dto){
		List<ExamPartDTO> expdto = new ArrayList<ExamPartDTO>();
		
//		String username = principal.getName(); 
		Enrollment enrollment = enrollmentS.findById(dto.getExamDTO().getEnrollmentDTO().getId());
		Exam exam = examS.findById(dto.getExamDTO().getId());
		ExamPartType examPartType = examPartTypeS.findById(dto.getExamPartTypeDTO().getId());
		ExamPartStatus exp = examPartStatusS.expsByCode("cr");
		List<Enrollment> enrollments = enrollment.getCourseInstance().getEnrollments();
		for(Enrollment e : enrollments) {
			ExamPart exampart = new ExamPart();
			Student st = e.getStudent();
			enrollment.setStudent(st);
			enrollment = enrollmentS.save(enrollment);
			
			exampart.setPoints(dto.getPoints());
			exampart.setDate(dto.getDate());
			exampart.setLocation(dto.getLocation());
			exampart.setExam(exam);
			exampart.setExamPartType(examPartType);
			exampart.setExamPartStatus(exp);
			
			exampart = examPartS.save(exampart);
			expdto.add(new ExamPartDTO(exampart));
		}
		
		return new ResponseEntity<List<ExamPartDTO>>(expdto, HttpStatus.CREATED);
	}
}
