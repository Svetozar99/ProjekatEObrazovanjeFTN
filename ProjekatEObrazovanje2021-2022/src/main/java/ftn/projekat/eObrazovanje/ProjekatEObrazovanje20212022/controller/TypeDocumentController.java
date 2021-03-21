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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.TeachingTypeDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.TypeDocumentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TeachingType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TypeDocument;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeachingTypeI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TypeDocumentServiceInterface;

@RestController
@RequestMapping(value = "api/type-document")
public class TypeDocumentController {
	
	@Autowired
	private TypeDocumentServiceInterface typeS;
	
	@GetMapping
	public ResponseEntity<List<TypeDocumentDTO>> getAllTypeDocuments(){
		List<TypeDocument> types = typeS.findAll();
		
		List<TypeDocumentDTO> dtos = new ArrayList<TypeDocumentDTO>();
		
		for(TypeDocument t : types) {
			dtos.add(new TypeDocumentDTO(t));
		}
		
		return new ResponseEntity<List<TypeDocumentDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<TypeDocumentDTO> getOneTypeDocument(@PathVariable("id") Long id){
		TypeDocument t = typeS.findById(id);
		
		return new ResponseEntity<TypeDocumentDTO>(new TypeDocumentDTO(t), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<TypeDocumentDTO> updateTypeDocument(@RequestBody TypeDocumentDTO dto){
		TypeDocument t = typeS.findById(dto.getId());
		
		if(t == null) {
			return new ResponseEntity<TypeDocumentDTO>(HttpStatus.NOT_FOUND);
		}
		t.setName(dto.getName());
		t.setCode(dto.getCode());
		t = typeS.save(t);
		return new ResponseEntity<TypeDocumentDTO>(new TypeDocumentDTO(t), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TypeDocumentDTO> saveTypeDocument(@RequestBody TeachingTypeDTO dto){
		TypeDocument t = new TypeDocument();
		t.setName(dto.getName());
		t.setCode(dto.getCode());
		
		t = typeS.save(t);
		
		return new ResponseEntity<TypeDocumentDTO>(new TypeDocumentDTO(t), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteTypeDocument(@PathVariable("id") Long id){
		TypeDocument t = typeS.findById(id);
		
		if(t != null) {
			typeS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
