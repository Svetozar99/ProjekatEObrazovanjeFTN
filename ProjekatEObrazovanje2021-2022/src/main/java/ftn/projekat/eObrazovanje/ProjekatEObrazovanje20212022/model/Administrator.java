package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrators")
public class Administrator extends User {

	public Administrator(Long id, String firstName, String lastName, String username, String password, UserRole role) {
		super(id, firstName, lastName, username, password, role);
	}

	
}
