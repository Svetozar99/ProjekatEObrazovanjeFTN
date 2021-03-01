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
<<<<<<< HEAD
import javax.persistence.ManyToMany;
=======
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
>>>>>>> branch 'master' of https://github.com/Svetozar99/ProjekatEObrazovanjeFTN.git
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "predmeti")
public class Predmet extends JpaEntity {

	@Column(name = "nazivPredmeta", nullable = false)
	private String nazivPredmeta;
	
	@Column(name = "brojEspb", nullable = false)
	private int brojEspb;
	
<<<<<<< HEAD
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Student> listaStudenata;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Nastavnik> listaNastavnika;
=======
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Predaje> listaPredavanja;
>>>>>>> branch 'master' of https://github.com/Svetozar99/ProjekatEObrazovanjeFTN.git
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="predmet")
	private List<Polaganje> polaganja = new ArrayList<Polaganje>();

<<<<<<< HEAD
	public Predmet(Long id, String nazivPredmeta, int brojEspb, Set<Student> listaStudenata,
			Set<Nastavnik> listaNastavnika) {
=======
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=true)
	private Student student;
	
	public Predmet(Long id, String nazivPredmeta, int brojEspb,Set<Predaje> listaPredavanja) {
>>>>>>> branch 'master' of https://github.com/Svetozar99/ProjekatEObrazovanjeFTN.git
		super(id);
		this.nazivPredmeta = nazivPredmeta;
		this.brojEspb = brojEspb;
<<<<<<< HEAD
		this.listaStudenata = listaStudenata;
		this.listaNastavnika = listaNastavnika;
=======
		this.listaPredavanja = listaPredavanja;
>>>>>>> branch 'master' of https://github.com/Svetozar99/ProjekatEObrazovanjeFTN.git
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

<<<<<<< HEAD
	public Set<Student> getListaStudenata() {
		return listaStudenata;
	}

	public void setListaStudenata(Set<Student> listaStudenata) {
		this.listaStudenata = listaStudenata;
	}

	public Set<Nastavnik> getListaPredavanja() {
		return listaNastavnika;
=======
	public Set<Predaje> getListaPredavanja() {
		return listaPredavanja;
>>>>>>> branch 'master' of https://github.com/Svetozar99/ProjekatEObrazovanjeFTN.git
	}

	public void setListaPredavanja(Set<Nastavnik> listaNastavnika) {
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
