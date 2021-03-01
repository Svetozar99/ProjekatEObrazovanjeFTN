package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Column;

public abstract class Korisnik extends JpaEntity{
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "korisnicko", nullable = false)
	private String korisnicko;
	
	@Column(name = "lozinka", nullable = false)
	private String lozinka;
	
	@Column(name = "uloga_korisnika", nullable = false)
	private KorisnikUloga ulogaKorisnika;

	public Korisnik(Long id, String ime, String prezime, String korisnicko, String lozinka,
			KorisnikUloga ulogaKorisnika) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.korisnicko = korisnicko;
		this.lozinka = lozinka;
		this.ulogaKorisnika = ulogaKorisnika;
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

	public String getKorisnicko() {
		return korisnicko;
	}

	public void setKorisnicko(String korisnicko) {
		this.korisnicko = korisnicko;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public KorisnikUloga getUlogaKorisnika() {
		return ulogaKorisnika;
	}

	public void setUlogaKorisnika(KorisnikUloga ulogaKorisnika) {
		this.ulogaKorisnika = ulogaKorisnika;
	}
}
