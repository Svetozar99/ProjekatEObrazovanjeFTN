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
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AccountServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceInterface;

@RestController
@RequestMapping(value = "api/account")
public class AccountController {

	@Autowired
	public AccountServiceInterface accountServiceInterface;
	
	@Autowired
	public StudentServiceInterface studentServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAllAccount(){
		List<Account> accounts = accountServiceInterface.findAll();
		
		List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
		
		for (Account account : accounts) {
			accountDTOs.add(new AccountDTO(account));
		}
		return new ResponseEntity<List<AccountDTO>>(accountDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountDTO> getOneAccount(@PathVariable("id") Long id){
		Account account = accountServiceInterface.findById(id);
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDTO){
		Student student = studentServiceInterface.findById(accountDTO.getStudentDTO().getId());
		
		Account account = new Account();
		account.setAmount(accountDTO.getAmount());
		account.setStudent(student);
		
		account = accountServiceInterface.save(account);
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id){
		Account account = accountServiceInterface.findById(id);
		if(account != null) {
			accountServiceInterface.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
