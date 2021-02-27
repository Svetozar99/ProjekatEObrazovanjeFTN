package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "predmet")
public class Predmet extends JpaEntity {

	@Column(name = "nazivPredmeta", nullable = false)
	private String nazivPredmeta;
	
	@Column(name = "brojEspb", nullable = false)
	private int brojEspb;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Pohadjanje> listaPohadjanja;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Predaje> listaPredavanja;

	public Predmet(Long id, String nazivPredmeta, int brojEspb, Set<Pohadjanje> listaPohadjanja,
			Set<Predaje> listaPredavanja) {
		super(id);
		this.nazivPredmeta = nazivPredmeta;
		this.brojEspb = brojEspb;
		this.listaPohadjanja = listaPohadjanja;
		this.listaPredavanja = listaPredavanja;
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

	public Set<Pohadjanje> getListaPohadjanja() {
		return listaPohadjanja;
	}

	public void setListaPohadjanja(Set<Pohadjanje> listaPohadjanja) {
		this.listaPohadjanja = listaPohadjanja;
	}

	public Set<Predaje> getListaPredavanja() {
		return listaPredavanja;
	}

	public void setListaPredavanja(Set<Predaje> listaPredavanja) {
		this.listaPredavanja = listaPredavanja;
	}
	
	
}
