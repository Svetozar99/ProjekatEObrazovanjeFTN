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
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.DocumentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Document;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TypeDocument;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.DocumentServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TypeDocumentServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;

@RestController
@RequestMapping(value = "api/document")
public class DocumentController {

	@Autowired
	private DocumentServiceInterface documentS;
	
	@Autowired
	private StudentServiceI studentS;
	
	@Autowired
	private TypeDocumentServiceInterface documentTypeS;
	
	@GetMapping
	public ResponseEntity<List<DocumentDTO>> getAllDocuments(){
		List<Document> documents = documentS.findAll();
		
		List<DocumentDTO> dtos = new ArrayList<DocumentDTO>();
		
		for (Document document : documents) {
			dtos.add(new DocumentDTO(document));
		}
		return new ResponseEntity<List<DocumentDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DocumentDTO> getOneDocument(@PathVariable("id") Long id){
		Document document = documentS.findById(id);
		if(document == null) {
			return new ResponseEntity<DocumentDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DocumentDTO>(new DocumentDTO(document), HttpStatus.OK);
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<DocumentDTO> updateDocument(@RequestBody DocumentDTO dto){
		
		Student student = studentS.findById(dto.getStudentDTO().getId());
		TypeDocument typeDocument = documentTypeS.findById(dto.getTypeDocumentDTO().getId());
		Document document = documentS.findById(dto.getId());
		if(document == null) {
			return new ResponseEntity<DocumentDTO>(HttpStatus.NOT_FOUND);
		}
		document.setTitle(dto.getTitle());
		document.setUrl(dto.getUrl());
		document.setStudent(student);
		document.setTypeDocument(typeDocument);
		document = documentS.save(document);
		return new ResponseEntity<DocumentDTO>(new DocumentDTO(document), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<DocumentDTO> saveDocument(@RequestBody DocumentDTO dto){
		Student student = studentS.findById(dto.getStudentDTO().getId());
		TypeDocument typeDocument = documentTypeS.findById(dto.getTypeDocumentDTO().getId());
		
		Document document = new Document();
		document.setTitle(dto.getTitle());
		document.setUrl(dto.getUrl());
		document.setStudent(student);
		document.setTypeDocument(typeDocument);
		document = documentS.save(document);
		return new ResponseEntity<DocumentDTO>(new DocumentDTO(document), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteDocument(@PathVariable("id") Long id){
		Document document = documentS.findById(id);
		if(document != null) {
			documentS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
