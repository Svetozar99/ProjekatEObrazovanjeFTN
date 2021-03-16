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
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AccountServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;

@RestController
@RequestMapping(value = "api/account")
public class AccountController {

	@Autowired
	public AccountServiceI accountService;
	
	@Autowired
	public StudentServiceI studentService;
	
	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAllAccount(){
		List<Account> accounts = accountService.findAll();
		
		List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
		
		for (Account account : accounts) {
			accountDTOs.add(new AccountDTO(account));
		}
		return new ResponseEntity<List<AccountDTO>>(accountDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountDTO> getOneAccount(@PathVariable("id") Long id){
		Account account = accountService.findById(id);
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO, @PathVariable("id") Long id){
		
		Student student = studentService.findById(accountDTO.getStudentDTO().getId());
		
		Account acc = accountService.findById(id);
		if(acc == null) {
			return new ResponseEntity<AccountDTO>(HttpStatus.BAD_REQUEST);
		}
		acc.setAmount(accountDTO.getAmount());
		acc.setStudent(student);
		return new ResponseEntity<AccountDTO>(new AccountDTO(acc), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDTO){
		Student student = studentService.findById(accountDTO.getStudentDTO().getId());
		
		Account account = new Account();
		account.setAmount(accountDTO.getAmount());
		account.setStudent(student);
		
		account = accountService.save(account);
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id){
		Account account = accountService.findById(id);
		if(account != null) {
			accountService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
