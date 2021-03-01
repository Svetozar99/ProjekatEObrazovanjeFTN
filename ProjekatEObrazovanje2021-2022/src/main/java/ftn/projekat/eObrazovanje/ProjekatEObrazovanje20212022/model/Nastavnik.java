package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nastavnici")
public class Nastavnik extends Korisnik {

	@Column(name = "uloga_nastavnika")
	private UlogaNastavnika ulogaNastavnika;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Predaje> listaPredavanja;

	public Nastavnik(Long id, String ime, String prezime, String korisnicko, String lozinka,
			KorisnikUloga ulogaKorisnika, UlogaNastavnika ulogaNastavnika, Set<Predaje> listaPredavanja) {
		super(id, ime, prezime, korisnicko, lozinka, ulogaKorisnika);
		this.ulogaNastavnika = ulogaNastavnika;
		this.listaPredavanja = listaPredavanja;
	}

	public UlogaNastavnika getUloga() {
		return ulogaNastavnika;
	}

	public void setUloga(UlogaNastavnika ulogaNastavnika) {
		this.ulogaNastavnika = ulogaNastavnika;
	}

	public Set<Predaje> getListaPredavanja() {
		return listaPredavanja;
	}

	public void setListaPredavanja(Set<Predaje> listaPredavanja) {
		this.listaPredavanja = listaPredavanja;
	}
}
