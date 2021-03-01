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
		dokumenti.add(dokument);
	}

	public void obrisiDokument(Dokument dokument) {
		dokument.setId(null);
		dokumenti.remove(dokument);
	}

	public List<Dokument> getDokumenti() {
		return dokumenti;
	}

	public void setDokumenti(List<Dokument> dokumenti) {
		this.dokumenti = dokumenti;
	}
}
