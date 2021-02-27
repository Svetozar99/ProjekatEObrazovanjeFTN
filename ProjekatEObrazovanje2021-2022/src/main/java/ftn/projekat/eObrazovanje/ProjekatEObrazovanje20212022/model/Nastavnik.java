package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nastavnik")
public class Nastavnik extends JpaEntity {

	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "uloga")
	private UlogaNastavnika uloga;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Predaje> listaPredavanja;

	public Nastavnik(Long id, String ime, String prezime, UlogaNastavnika uloga, Set<Predaje> listaPredavanja) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
		this.listaPredavanja = listaPredavanja;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public UlogaNastavnika getUloga() {
		return uloga;
	}

	public void setUloga(UlogaNastavnika uloga) {
		this.uloga = uloga;
	}

	public Set<Predaje> getListaPredavanja() {
		return listaPredavanja;
	}

	public void setListaPredavanja(Set<Predaje> listaPredavanja) {
		this.listaPredavanja = listaPredavanja;
	}
	
	
}
