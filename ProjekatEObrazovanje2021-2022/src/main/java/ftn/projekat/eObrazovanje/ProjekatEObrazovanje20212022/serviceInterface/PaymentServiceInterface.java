package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Payment;


public interface PaymentServiceInterface {

	public List<Payment> findAll();
	
	public Payment findById(Long id);
	
	public Payment save(Payment payment);
	
	public void delete(Long id);
	
	public List<Payment> findByUsername(String username);
}
