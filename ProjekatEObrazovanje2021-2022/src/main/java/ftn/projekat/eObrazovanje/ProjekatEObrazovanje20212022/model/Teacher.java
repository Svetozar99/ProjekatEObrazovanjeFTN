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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "teachers")
public class Teacher extends User {

	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "teacher")
	private List<Teaching> teching = new ArrayList<Teaching>();

	public Teacher(List<Teaching> teching) {
		super();
		this.teching = teching;
	}

	public List<Teaching> getTeching() {
		return teching;
	}

	public void setTeching(List<Teaching> teching) {
		this.teching = teching;
	}
}
