package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

public class Administrator extends Korisnik {

	public Administrator(Long id, String ime, String prezime, String korisnicko, String lozinka,
			KorisnikUloga ulogaKorisnika) {
		super(id, ime, prezime, korisnicko, lozinka, ulogaKorisnika);
	}
}
