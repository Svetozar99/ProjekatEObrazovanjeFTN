package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "nastavnici")
public class Nastavnik extends Korisnik {

	@Column(name = "uloga_nastavnika")
	private UlogaNastavnika ulogaNastavnika;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Predmet> listaPredmeta;

	public Nastavnik(Long id, String ime, String prezime, String korisnicko, String lozinka,
			KorisnikUloga ulogaKorisnika, UlogaNastavnika ulogaNastavnika, Set<Predmet> listaPredmeta) {
		super(id, ime, prezime, korisnicko, lozinka, ulogaKorisnika);
		this.ulogaNastavnika = ulogaNastavnika;
		this.listaPredmeta = listaPredmeta;
	}

	public UlogaNastavnika getUloga() {
		return ulogaNastavnika;
	}

	public void setUloga(UlogaNastavnika ulogaNastavnika) {
		this.ulogaNastavnika = ulogaNastavnika;
	}

	public Set<Predmet> getListaPredavanja() {
		return listaPredmeta;
	}

	public void setListaPredavanja(Set<Predmet> listaPredmeta) {
		this.listaPredmeta = listaPredmeta;
	}
}
