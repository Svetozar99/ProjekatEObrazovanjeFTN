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
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.PaymentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Payment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AccountServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.PaymentServiceInterface;

@RestController
@RequestMapping(value = "api/payment")
public class PaymentController {

	@Autowired
	private PaymentServiceInterface paymentS;
	
	@Autowired
	private AccountServiceI accountS;
	
	@GetMapping
	public ResponseEntity<List<PaymentDTO>> getAllPayments(){
		List<Payment> payments = paymentS.findAll();
		
		List<PaymentDTO> dtos = new ArrayList<PaymentDTO>();
		
		for(Payment p : payments) {
			dtos.add(new PaymentDTO(p));
		}
		
		return new ResponseEntity<List<PaymentDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PaymentDTO> getOnePayment(@PathVariable("id") Long id){
		Payment p = paymentS.findById(id);
		if(p == null) {
			return new ResponseEntity<PaymentDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PaymentDTO>(new PaymentDTO(p), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<PaymentDTO> updateTypeDocument(@RequestBody PaymentDTO dto){
		
		Account account = accountS.findById(dto.getAccountDTO().getId());
		Payment p = paymentS.findById(dto.getId());
		
		if(p == null) {
			return new ResponseEntity<PaymentDTO>(HttpStatus.NOT_FOUND);
		}
		p.setAccount(account);
		p.setAmount(dto.getAmount());
		p.setCurrency(dto.getCurrency());
		p.setDatePayment(dto.getDate());
		p.setNote(dto.getNote());
		p.setUrgently(dto.getUrgently());
		
		p = paymentS.save(p);
		
		return new ResponseEntity<PaymentDTO>(new PaymentDTO(p), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PaymentDTO> saveTypeDocument(@RequestBody PaymentDTO dto){
		
		Account account = accountS.findById(dto.getAccountDTO().getId());
		Payment p = new Payment();
		p.setAccount(account);
		p.setAmount(dto.getAmount());
		p.setCurrency(dto.getCurrency());
		p.setDatePayment(dto.getDate());
		p.setNote(dto.getNote());
		p.setUrgently(dto.getUrgently());
		
		p = paymentS.save(p);
		
		return new ResponseEntity<PaymentDTO>(new PaymentDTO(p), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteTypeDocument(@PathVariable("id") Long id){
		Payment t = paymentS.findById(id);
		
		if(t != null) {
			paymentS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
