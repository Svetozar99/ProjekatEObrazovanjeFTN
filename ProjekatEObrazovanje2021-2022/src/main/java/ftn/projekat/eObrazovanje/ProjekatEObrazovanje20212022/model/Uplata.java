package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Uplata extends JpaEntity {

	@Column(name = "svrha_uplate", nullable = false)
	private String svrhaUplate;
	
	@Column(name = "datum_uplate", nullable = false)
	private Date datumUplate;
	
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=true)
	private Student student;

	public Uplata(Long id, String svrhaUplate, Date datumUplate, Student student) {
		super(id);
		this.svrhaUplate = svrhaUplate;
		this.datumUplate = datumUplate;
		this.student = student;
	}

	public String getSvrhaUplate() {
		return svrhaUplate;
	}

	public void setSvrhaUplate(String svrhaUplate) {
		this.svrhaUplate = svrhaUplate;
	}

	public Date getDatumUplate() {
		return datumUplate;
	}

	public void setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
		student.getUplate().add(this);
	}
	
	public void obrisiStudenta(Student student) {
		this.student=null;
		student.getUplate().remove(this);
	}
}
