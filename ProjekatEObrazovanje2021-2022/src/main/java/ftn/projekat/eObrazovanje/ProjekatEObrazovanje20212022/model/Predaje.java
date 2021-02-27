package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "predaje")
public class Predaje extends JpaEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private Nastavnik nastavnik;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Predmet predmet;

	public Predaje(Long id, Nastavnik nastavnik, Predmet predmet) {
		super(id);
		this.nastavnik = nastavnik;
		this.predmet = predmet;
	}

	public Nastavnik getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	
	
}
