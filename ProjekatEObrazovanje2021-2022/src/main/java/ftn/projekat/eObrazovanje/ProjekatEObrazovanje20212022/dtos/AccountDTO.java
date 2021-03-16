package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;

public class AccountDTO {
	
	public Long id;
	
	public Double amount;
	
	public StudentDTO student;

	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(Long id, Double amount, StudentDTO student) {
		super();
		this.id = id;
		this.amount = amount;
		this.student = student;
	}

	public AccountDTO(Account account) {
		this(account.getId(), account.getAmount(), account.getStudent());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	
}
