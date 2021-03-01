package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administratori")
public class Administrator extends Korisnik {

	public Administrator(Long id, String ime, String prezime, String korisnicko, String lozinka,
			KorisnikUloga ulogaKorisnika) {
		super(id, ime, prezime, korisnicko, lozinka, ulogaKorisnika);
	}
}
