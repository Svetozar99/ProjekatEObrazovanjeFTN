package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "polaganja")
public class Enrollment extends JpaEntity {

	@Column(name = "naziv_nastavne_obaveze")
	private String nazivNastavneObaveze;
	
	@Column(name = "vreme_polaganja")
	private String vremePolaganja;
	
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=true)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="predmet_id", referencedColumnName="id", nullable=true)
	private CourseSpecification predmet;

	public Enrollment(Long id, String nazivNastavneObaveze, String vremePolaganja, Student student, CourseSpecification predmet) {
		super(id);
		this.nazivNastavneObaveze = nazivNastavneObaveze;
		this.vremePolaganja = vremePolaganja;
		this.student = student;
		this.predmet = predmet;
	}

	public String getNazivNastavneObaveze() {
		return nazivNastavneObaveze;
	}

	public void setNazivNastavneObaveze(String nazivNastavneObaveze) {
		this.nazivNastavneObaveze = nazivNastavneObaveze;
	}

	public String getVremePolaganja() {
		return vremePolaganja;
	}

	public void setVremePolaganja(String vremePolaganja) {
		this.vremePolaganja = vremePolaganja;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
		student.getPolaganja().add(this);
	}
	
	public void obrisiStudenta(Student student) {
		this.student = null;
		student.getPolaganja().remove(this);
	}

	public CourseSpecification getPredmet() {
		return predmet;
	}
}
