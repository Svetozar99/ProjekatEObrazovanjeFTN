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
@Table(name = "studenti")
public class Student extends Korisnik{
	
	@Column(name = "brojIndeksa", nullable = false)
	private String brojIndeksa;
	

	@OneToMany(fetch = LAZY)
	private Set<Predmet> listaPredmeta;
	

	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Dokument> dokumenti = new ArrayList<Dokument>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Uplata> uplate = new ArrayList<Uplata>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Polaganje> polaganja = new ArrayList<Polaganje>();

	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Predmet> predmeti = new ArrayList<Predmet>();
	
	public Student(Long id, String ime, String prezime, String korisnicko, String lozinka, KorisnikUloga ulogaKorisnika,
			String brojIndeksa) {
		super(id, ime, prezime, korisnicko, lozinka, ulogaKorisnika);
		this.brojIndeksa = brojIndeksa;
	}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}


	public void dodajDokument(Dokument dokument) {
		dokument.setStudent(this);
	}

	public void obrisiDokument(Dokument dokument) {
		dokument.obrisiStudenta(this);
	}

	public List<Dokument> getDokumenti() {
		return dokumenti;
	}

	public void setDokumenti(List<Dokument> dokumenti) {
		this.dokumenti = dokumenti;
	}

	public List<Uplata> getUplate() {
		return uplate;
	}

	public void setUplate(List<Uplata> uplate) {
		this.uplate = uplate;
	}
	
	public void dodajUplatu(Uplata uplata) {
		uplata.setStudent(this);
	}
	
	public void obrisiUplatu(Uplata uplata) {
		uplata.obrisiStudenta(this);
	}
	
	public List<Polaganje> getPolaganja() {
		return polaganja;
	}

	public void setPolaganja(List<Polaganje> polaganja) {
		this.polaganja = polaganja;
	}
	
	public void dodajPolaganje(Polaganje polaganje) {
		polaganje.setStudent(this);
	}
	
	public void obrisiPolaganje(Polaganje polaganje) {
		polaganje.obrisiStudenta(this);
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	public void dodajPredmet(Predmet predmet) {
		predmet.setStudent(this);
	}
	
	public void obrisiPolaganje(Predmet predmet) {
		predmet.obrisiStudenta(this);
	} 
}
