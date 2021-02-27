package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends JpaEntity{

	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "brojIndeksa", nullable = false)
	private String brojIndeksa;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Pohadjanje> listaPohadjanja;

	public Student(Long id, String ime, String prezime, String brojIndeksa, Set<Pohadjanje> listaPohadjanja) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.brojIndeksa = brojIndeksa;
		this.listaPohadjanja = listaPohadjanja;
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
	
	
}
