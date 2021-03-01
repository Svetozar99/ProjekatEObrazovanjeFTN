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
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="predmet")
	private List<Polaganje> polaganja = new ArrayList<Polaganje>();

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
}
