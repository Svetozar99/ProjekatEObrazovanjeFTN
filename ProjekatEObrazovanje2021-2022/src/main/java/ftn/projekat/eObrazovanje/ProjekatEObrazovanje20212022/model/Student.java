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
	private Set<Pohadjanje> listaPohadjanja;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Dokument> dokumenti = new ArrayList<Dokument>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Uplata> uplate = new ArrayList<Uplata>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Polaganje> polaganja = new ArrayList<Polaganje>();

	public Student(Long id, String ime, String prezime, String korisnicko, String lozinka, KorisnikUloga ulogaKorisnika,
			String brojIndeksa, Set<Pohadjanje> listaPohadjanja) {
		super(id, ime, prezime, korisnicko, lozinka, ulogaKorisnika);
		this.brojIndeksa = brojIndeksa;
		this.listaPohadjanja = listaPohadjanja;
	}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public Set<Pohadjanje> getListaPohadjanja() {
		return listaPohadjanja;
	}

	public void setListaPohadjanja(Set<Pohadjanje> listaPohadjanja) {
		this.listaPohadjanja = listaPohadjanja;
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
}
