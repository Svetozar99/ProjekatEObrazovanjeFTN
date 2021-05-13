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
	
	@GetMapping(value = "/student/{courseId}")
//	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> getAllForCardNumber(@PathVariable("courseId") Long courseId,Principal principal){
		System.out.println("\nZa studenta");
		String name = principal.getName(); //get logged in username
		Student st = studServ.findByUser(name);
		List<ExamPart> examParts = examPartS.findByCardNumAndCourse(st.getCardNumber(), courseId);
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		for (ExamPart examPart : examParts) {
			dtos.add(new ExamPartDTO(examPart));
		}
		System.out.println(examParts.size());
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{courseId}/{cardNumber}")
//	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> getAllForCardNumber(@PathVariable("courseId") Long courseId,@PathVariable("cardNumber") String cardNumber){
		System.out.println("\nZa odredjenog studenta");
		List<ExamPart> examParts = examPartS.findByCardNumAndCourse(cardNumber, courseId);
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		for (ExamPart examPart : examParts) {
			dtos.add(new ExamPartDTO(examPart));
		}
		System.out.println(examParts.size());
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/course-instance/{courseId}")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> getAllForCourseInstance(@PathVariable("courseId") Long courseId){
		System.out.println("\nGet all parts for ADMINISTRATOR");
		List<ExamPart> examParts = examPartS.findByCourseInstance(courseId);
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		for (ExamPart examPart : examParts) {
			if(!examPartS.isIn(examPart,dtos)) {
				dtos.add(new ExamPartDTO(examPart));
			}
		}
		System.out.println(examParts.size());
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")//izmjeniti ovo kad dodje vrijeme
	public ResponseEntity<ExamPartDTO> getOneExamPart(@PathVariable("id") Long id){
		ExamPart examPart = examPartS.findById(id);
		System.out.println("\nZa administratora");
		if(examPart == null) {
			return new ResponseEntity<ExamPartDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExamPartDTO>(new ExamPartDTO
				(examPart), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> updateExamParts(@RequestBody ExamPartDTO dto){
		List<ExamPart> examParts = examPartS.findByCode(dto.getCode());
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		if(examParts.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		for (ExamPart examPart : examParts) {
			ExamPartType examPartType = examPartTypeS.findById(dto.getExamPartTypeDTO().getId());
			examPart.setDate(dto.getDate());
			examPart.setLocation(dto.getLocation());
			examPart.setPoints(dto.getPoints());
			examPart.setExamPartType(examPartType);
			
			examPart = examPartS.save(examPart);
			dtos.add(new ExamPartDTO(examPart));
		}
		
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@PutMapping(value = "/one-exam-part")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> updateExamPart(@RequestBody ExamPartDTO dto){
		System.out.println("\nAzuriram examPart");
		ExamPart examPart = examPartS.findById(dto.getId());
		ExamPartStatus examPartStatus = examPartStatusS.expsByCode(dto.getStatusDTO().getCode());
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		if(examPart == null) {
			return ResponseEntity.notFound().build();
		}
		examPart.setExamPartStatus(examPartStatus);
		examPart.setWonPoints(dto.getWonPoints());
		
		examPart = examPartS.save(examPart);
		
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> saveExamPart(@RequestBody ExamPartDTO dto){
		System.out.println("\n---->Save exam part<------\n");
		List<Exam> exams = examS.findByCourseInstance(dto.getExamDTO().getEnrollmentDTO().getCourseInstanceDTO().getId());
		ExamPartType examPartType = examPartTypeS.findByCode(dto.getExamPartTypeDTO().getCode());
		ExamPartStatus examPartStatus = examPartStatusS.expsByCode("cr");
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		long maxId = examPartS.maxId()+1;
		String code = dto.getExamDTO().getEnrollmentDTO().getCourseInstanceDTO().getId()+"-"+maxId;
		for (Exam exam : exams) {
			ExamPart examPart = new ExamPart();
			examPart.setDate(dto.getDate());
			examPart.setLocation(dto.getLocation());
			examPart.setPoints(dto.getPoints());
			examPart.setWonPoints(0);
			examPart.setExam(exam);
			examPart.setExamPartType(examPartType);
			examPart.setExamPartStatus(examPartStatus);
			examPart.setCode(code);
			
			examPart = examPartS.save(examPart);
			dtos.add(new ExamPartDTO(examPart));
		}
			
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteExamPart(@PathVariable("id") Long id){
		ExamPart examPart = examPartS.findById(id);
		if(examPart != null) {
			examPartS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/my-exam-parts/{code}")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
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
			exam.getExamParts().add(exampart);
			if(exampart.getExamPartStatus().getCode().equals("pa")) {
				exampart.getExam().setPoints();
				examS.save(exampart.getExam());
			}
			
			expdto.add(new ExamPartDTO(exampart));
		}
		
		return new ResponseEntity<List<ExamPartDTO>>(expdto, HttpStatus.CREATED);
	}
}
