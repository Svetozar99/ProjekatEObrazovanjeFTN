package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pohadjanje")
public class Pohadjanje extends JpaEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Predmet predmet;

	public Pohadjanje(Long id, Student student, Predmet predmet) {
		super(id);
		this.student = student;
		this.predmet = predmet;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	
	
}
