package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dokumenti")
public class Dokument extends JpaEntity{
	
	@Column(name = "naziv_dokumenta", nullable = false)
	private String nazivDokumenta;
	
	@Column(name = "datum_izadavanja", nullable = false)
	private Date datumIzdavanja;
	
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=true)
	private Student student;

	public Dokument(Long id, String nazivDokumenta, Date datumIzdavanja, Student student) {
		super(id);
		this.nazivDokumenta = nazivDokumenta;
		this.datumIzdavanja = datumIzdavanja;
		this.student = student;
	}

	public String getNazivDokumenta() {
		return nazivDokumenta;
	}

	public void setNazivDokumenta(String nazivDokumenta) {
		this.nazivDokumenta = nazivDokumenta;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
		student.getDokumenti().add(this);
	}
	
	public void obrisiStudenta(Student student) {
		this.student = null;
		student.getDokumenti().remove(this);
	}
}
