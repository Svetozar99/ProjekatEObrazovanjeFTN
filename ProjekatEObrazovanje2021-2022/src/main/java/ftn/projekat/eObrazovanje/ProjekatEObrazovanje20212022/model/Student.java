package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User{
	
	@Column(name = "card_number", nullable = false)
	private String cardNumber;

	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Enrollment> enrollments = new ArrayList<Enrollment>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Payment> payments = new ArrayList<Payment>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Document> documents = new ArrayList<Document>();

	public Student(String cardNumber, List<Enrollment> enrollments, List<Payment> payments, List<Document> documents) {
		super();
		this.cardNumber = cardNumber;
		this.enrollments = enrollments;
		this.payments = payments;
		this.documents = documents;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
}
