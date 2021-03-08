package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "predmeti")
public class Predmet extends JpaEntity {

	@Column(name = "nazivPredmeta", nullable = false)
	private String nazivPredmeta;
	
	@Column(name = "brojEspb", nullable = false)
	private int brojEspb;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="predmet")
	private List<Polaganje> polaganja = new ArrayList<Polaganje>();

	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=true)
	private Student student;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Nastavnik> listaNastavnika;
	
	
	public Predmet(Long id, String nazivPredmeta, int brojEspb, List<Polaganje> polaganja, Student student) {
		super(id);
		this.nazivPredmeta = nazivPredmeta;
		this.brojEspb = brojEspb;
		this.polaganja = polaganja;
		this.student = student;
	}
	
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public int getBrojEspb() {
		return brojEspb;
	}

	public void setBrojEspb(int brojEspb) {
		this.brojEspb = brojEspb;
	}

	public Student getStudent() {
		return student;
	}

	public Set<Nastavnik> getListaNastavnika() {
		return listaNastavnika;
	}

	public void setListaNastavnika(Set<Nastavnik> listaNastavnika) {
		this.listaNastavnika = listaNastavnika;
	}

	public List<Polaganje> getPolaganja() {
		return polaganja;
	}

	public void setPolaganja(List<Polaganje> polaganja) {
		this.polaganja = polaganja;
	}
	
	public void dodajPolaganje(Polaganje polaganje) {
		polaganje.setPredmet(this);
	}
	
	public void obrisiPolaganje(Polaganje polaganje) {
		polaganje.obrisiPredmet(this);
	}
	
	public void setStudent(Student student) {
		this.student = student;
		student.getPredmeti().add(this);
	}
	
	public void obrisiStudenta(Student student) {
		this.student=null;
		student.getPredmeti().remove(this);
	}
}
