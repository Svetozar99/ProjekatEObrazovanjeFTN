package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment extends JpaEntity {

	@Column(name = "currency", nullable = false)
	private String currency;
	
	@Column(name = "amount", nullable = false)
	private Double amount;
	
	@Column(name = "date_payment", nullable = false)
	private Date datePayment;
	
	@Column(name = "urgently", nullable = false)
	private Boolean urgently;
	
	@Column(name = "type_payment", nullable = false)
	private TypePayment typePayment;
	
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=false)
	private Student student;

	public Payment(Long id, String currency, Double amount, Date datePayment, Boolean urgently, TypePayment typePayment,
			Student student) {
		super(id);
		this.currency = currency;
		this.amount = amount;
		this.datePayment = datePayment;
		this.urgently = urgently;
		this.typePayment = typePayment;
		this.student = student;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public Boolean getUrgently() {
		return urgently;
	}

	public void setUrgently(Boolean urgently) {
		this.urgently = urgently;
	}

	public TypePayment getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
